package com.example.juegopreguntas.Cuerpo

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.juegopreguntas.R
import com.example.juegopreguntas.databinding.FragmentPuntajesBinding

class PuntajesFragment : Fragment() {

    private var _binding: FragmentPuntajesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPuntajesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener puntajes de SharedPreferences y mostrarlos en el RecyclerView
        val puntajes = obtenerPuntajesDesdeSharedPreferences()
        val adapter = PuntajesAdapter(puntajes)
        binding.recyclerViewPuntajes.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewPuntajes.adapter = adapter
    }

    private fun obtenerPuntajesDesdeSharedPreferences(): List<Pair<String, Int>> {
        val sharedPreferences = requireActivity().getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)
        val puntajes = mutableListOf<Pair<String, Int>>()

        // Iterar sobre todas las claves en SharedPreferences
        for (key in sharedPreferences.all.keys) {
            if (key.startsWith("puntaje_maximo_")) {
                val nombreUsuario = key.substringAfter("puntaje_maximo_")
                val puntaje = sharedPreferences.getInt(key, 0)
                puntajes.add(Pair(nombreUsuario, puntaje))
            }
        }

        // Ordenar la lista por puntaje (descendente)
        puntajes.sortByDescending { it.second }
        return puntajes
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpiar la referencia al binding
    }
}