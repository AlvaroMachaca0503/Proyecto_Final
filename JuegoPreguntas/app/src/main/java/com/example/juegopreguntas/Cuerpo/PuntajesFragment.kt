// Creador: Enyelbert Anderson Panta Huaracha
// Fecha de creación: 11 de diciembre de 2024
// Descripción: Fragmento que muestra la lista de puntajes.

package com.example.juegopreguntas.Cuerpo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.juegopreguntas.DataManager
import com.example.juegopreguntas.databinding.FragmentPuntajesBinding

class PuntajesFragment : Fragment() {

    private var _binding: FragmentPuntajesBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataManager: DataManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar el layout y obtener la referencia al DataManager.
        _binding = FragmentPuntajesBinding.inflate(inflater, container, false)
        val view = binding.root
        dataManager = DataManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener la lista de puntajes.
        val puntajes = dataManager.obtenerPuntajes()
        // Crear el adaptador y configurarlo en el RecyclerView.
        val adapter = PuntajesAdapter(puntajes)
        binding.recyclerViewPuntajes.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewPuntajes.adapter = adapter

        // Agregar separadores entre los elementos de la lista.
        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.recyclerViewPuntajes.addItemDecoration(dividerItemDecoration)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpiar la referencia al binding.
    }
}