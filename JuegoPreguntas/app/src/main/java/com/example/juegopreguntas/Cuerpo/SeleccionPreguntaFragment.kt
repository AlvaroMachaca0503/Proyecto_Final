// Creador: Alvaro Andre Machaca Melendez
// Fecha de creación: 11 de diciembre de 2024
// Descripción: Fragmento para seleccionar una categoría de preguntas.

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
        // Obtener la lista de nombres de categorías.
        categorias = dataManager.obtenerCategorias().map { it.nombre }

        // Configurar el cambio automático de categorías.
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable { cambiarCategoria() }
        handler.postDelayed(runnable, 100) // Cambiar cada 100ms

        // Configurar el botón para detener el cambio y navegar al juego.
        binding.btnStop.setOnClickListener {
            handler.removeCallbacks(runnable) // Detener el cambio

            if (categorias.isNotEmpty()) {
                val nombreCategoria = categorias[indiceCategoriaActual]
                println("Nombre de la categoría: $nombreCategoria")

                val bundle = Bundle()
                bundle.putString("categoria", nombreCategoria)
                findNavController().navigate(R.id.action_seleccionPreguntaFragment_to_juegoFragment, bundle)
            } else {
                // Mostrar un mensaje si no hay categorías.
                Toast.makeText(requireContext(), "No se encontraron categorías", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    // Cambia la categoría mostrada en el TextView.
    private fun cambiarCategoria() {
        if (categorias.isNotEmpty()) {
            indiceCategoriaActual = (indiceCategoriaActual + 1) % categorias.size
            binding.tvCategoria.text = categorias[indiceCategoriaActual]
            handler.postDelayed(runnable, 100)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(runnable) // Detener el cambio de categorías.
        _binding = null
    }
}
