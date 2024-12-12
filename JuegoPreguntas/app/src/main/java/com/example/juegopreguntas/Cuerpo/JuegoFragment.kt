package com.example.juegopreguntas.Cuerpo

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.juegopreguntas.DataManager
import com.example.juegopreguntas.Pregunta
import com.example.juegopreguntas.Categoria
import com.example.juegopreguntas.R
import com.example.juegopreguntas.databinding.FragmentJuegoBinding

class JuegoFragment : Fragment() {

    private var _binding: FragmentJuegoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GameViewModel by activityViewModels()
    private lateinit var dataManager: DataManager
    private lateinit var categoriaActual: Categoria
    private lateinit var preguntaActual: Pregunta
    private var puntaje = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJuegoBinding.inflate(inflater, container, false)
        val view = binding.root

        dataManager = DataManager(requireContext())

        // Obtener la categoría seleccionada desde los argumentos
        val nombreCategoria = arguments?.getString("categoria") ?: ""
        println("Nombre de la categoría: $nombreCategoria")
        categoriaActual = dataManager.obtenerCategorias().find { it.nombre == nombreCategoria }
            ?: run {
                // Manejar el caso de categoría no encontrada
                Toast.makeText(requireContext(), "Error al cargar la categoría", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
                return view
            }

        viewModel.reiniciarIndicePregunta()
        mostrarPregunta()

        // Configurar listeners para los RadioButtons
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val respuestaSeleccionada = when (checkedId) {
                R.id.opcion1RadioButton -> 0
                R.id.opcion2RadioButton -> 1
                R.id.opcion3RadioButton -> 2
                R.id.opcion4RadioButton -> 3
                else -> -1
            }

            if (respuestaSeleccionada != -1) {
                verificarRespuesta(respuestaSeleccionada)
            }
        }

        return view
    }

    private fun mostrarPregunta() {
        if (viewModel.indicePreguntaActual.value!! < categoriaActual.preguntas.size) {
            preguntaActual = categoriaActual.preguntas[viewModel.indicePreguntaActual.value!!]

            binding.preguntaTextView.text = preguntaActual.texto
            binding.opcion1RadioButton.text = preguntaActual.opcion1
            binding.opcion2RadioButton.text = preguntaActual.opcion2
            binding.opcion3RadioButton.text = preguntaActual.opcion3
            binding.opcion4RadioButton.text = preguntaActual.opcion4

            // Reiniciar colores de las opciones
            binding.opcion1RadioButton.setTextColor(Color.BLACK)
            binding.opcion2RadioButton.setTextColor(Color.BLACK)
            binding.opcion3RadioButton.setTextColor(Color.BLACK)
            binding.opcion4RadioButton.setTextColor(Color.BLACK)
        } else {
            // No hay más preguntas en esta categoría
            Toast.makeText(requireContext(), "No hay más preguntas en esta categoría", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_juegoFragment_to_puntajesFragment)
        }
    }

    private fun verificarRespuesta(respuestaSeleccionada: Int) {
        if (respuestaSeleccionada == preguntaActual.respuestaCorrecta) {
            puntaje++
            // Mostrar la interfaz "Correcta"
            findNavController().navigate(R.id.action_juegoFragment_to_correctFragment)
        } else {
            // Mostrar la interfaz "Incorrecta"
            findNavController().navigate(R.id.action_juegoFragment_to_incorrectFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}