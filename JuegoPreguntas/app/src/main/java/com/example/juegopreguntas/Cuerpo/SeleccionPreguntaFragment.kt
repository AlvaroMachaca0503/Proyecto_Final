package com.example.juegopreguntas.Cuerpo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.juegopreguntas.DataManager
import com.example.juegopreguntas.R
import com.example.juegopreguntas.databinding.FragmentSeleccionPreguntaBinding

class SeleccionPreguntaFragment : Fragment() {

    private var _binding: FragmentSeleccionPreguntaBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataManager: DataManager
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private lateinit var categorias: List<String>
    private var indiceCategoriaActual = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeleccionPreguntaBinding.inflate(inflater, container, false)
        val view = binding.root

        dataManager = DataManager(requireContext())
        categorias = dataManager.obtenerCategorias().map { it.nombre }

        handler = Handler(Looper.getMainLooper())
        runnable = Runnable { cambiarCategoria() }
        handler.postDelayed(runnable, 500) // Cambiar categoría cada 500ms

        binding.btnStop.setOnClickListener {
            handler.removeCallbacks(runnable) // Detener el cambio de categorías

            if (categorias.isNotEmpty()) { // Verificar si la lista no está vacía
                // Navegar a JuegoFragment, pasando la categoría seleccionada
                val bundle = Bundle()
                bundle.putString("categoria", categorias[indiceCategoriaActual])
                findNavController().navigate(R.id.action_seleccionPreguntaFragment_to_juegoFragment, bundle)
            } else {
                // Manejar el caso de lista vacía, por ejemplo, mostrar un mensaje de error
                Toast.makeText(requireContext(), "No se encontraron categorías", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun cambiarCategoria() {
        if (categorias.isNotEmpty()) { // Verificar si la lista no está vacía
            indiceCategoriaActual = (indiceCategoriaActual + 1) % categorias.size
            binding.tvCategoria.text = categorias[indiceCategoriaActual]
            handler.postDelayed(runnable, 500)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(runnable)
        _binding = null
    }
}