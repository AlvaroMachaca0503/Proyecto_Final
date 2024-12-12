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
        _binding = FragmentPuntajesBinding.inflate(inflater, container, false)
        val view = binding.root
        dataManager = DataManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener puntajes del DataManager y mostrarlos en el RecyclerView
        val puntajes = dataManager.obtenerPuntajes()
        val adapter = PuntajesAdapter(puntajes)
        binding.recyclerViewPuntajes.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewPuntajes.adapter = adapter

        // Agregar un ItemDecoration para dibujar las líneas de la tabla
        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.recyclerViewPuntajes.addItemDecoration(dividerItemDecoration)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}