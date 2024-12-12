package com.example.juegopreguntas.Cuerpo

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.juegopreguntas.DataManager
import com.example.juegopreguntas.R
import com.example.juegopreguntas.databinding.FragmentJuegoBinding
import com.example.juegopreguntas.Categoria

class JuegoFragment : Fragment() {

    private var _binding: FragmentJuegoBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataManager: DataManager
    private lateinit var categoriaActual: Categoria
    private var indicePregunta = 0
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
        categoriaActual = dataManager.obtenerCategorias().find { it.nombre == nombreCategoria }
            ?: throw IllegalArgumentException("Categoría no encontrada")

        mostrarPregunta()

        binding.siguienteButton.setOnClickListener {
            val idRadioButtonSeleccionado = when {
                binding.opcion1RadioButton.isChecked -> R.id.opcion1RadioButton
                binding.opcion2RadioButton.isChecked -> R.id.opcion2RadioButton
                binding.opcion3RadioButton.isChecked -> R.id.opcion3RadioButton
                binding.opcion4RadioButton.isChecked -> R.id.opcion4RadioButton
                else -> -1 // Ninguna opción seleccionada
            }

            if (idRadioButtonSeleccionado == -1) {
                Toast.makeText(context, "Por favor, seleccione una respuesta", Toast.LENGTH_SHORT).show()
            } else {
                verificarRespuesta(idRadioButtonSeleccionado)
            }
        }

        return view
    }

    private fun mostrarPregunta() {
        val preguntaActual = categoriaActual.preguntas[indicePregunta]

        binding.preguntaTextView.text = preguntaActual.texto
        binding.opcion1RadioButton.text = preguntaActual.opcion1
        binding.opcion2RadioButton.text = preguntaActual.opcion2
        binding.opcion3RadioButton.text = preguntaActual.opcion3
        binding.opcion4RadioButton.text = preguntaActual.opcion4

        // Reiniciar colores de las opciones y estado de selección
        binding.opcion1RadioButton.setTextColor(Color.BLACK)
        binding.opcion2RadioButton.setTextColor(Color.BLACK)
        binding.opcion3RadioButton.setTextColor(Color.BLACK)
        binding.opcion4RadioButton.setTextColor(Color.BLACK)
        binding.radioGroup.clearCheck()
    }

    private fun verificarRespuesta(idRadioButtonSeleccionado: Int) {
        val preguntaActual = categoriaActual.preguntas[indicePregunta]
        val respuestaCorrecta = when (preguntaActual.respuestaCorrecta) {
            0 -> R.id.opcion1RadioButton
            1 -> R.id.opcion2RadioButton
            2 -> R.id.opcion3RadioButton
            3 -> R.id.opcion4RadioButton
            else -> -1 // No debería ocurrir
        }

        if (idRadioButtonSeleccionado == respuestaCorrecta) {
            puntaje++
            view?.findViewById<RadioButton>(idRadioButtonSeleccionado)?.setTextColor(Color.GREEN)
            Toast.makeText(context, "¡Correcto!", Toast.LENGTH_SHORT).show()
        } else {
            view?.findViewById<RadioButton>(idRadioButtonSeleccionado)?.setTextColor(Color.RED)
            view?.findViewById<RadioButton>(respuestaCorrecta)?.setTextColor(Color.GREEN)
            Toast.makeText(context, "Incorrecto", Toast.LENGTH_SHORT).show()
        }

        // Pasar a la siguiente pregunta o finalizar el juego
        indicePregunta++
        if (indicePregunta < categoriaActual.preguntas.size) {
            mostrarPregunta()
        } else {
            // Navegar al PuntajesFragment y pasar el puntaje
            val bundle = Bundle()
            bundle.putInt("puntaje", puntaje)

            // Guarda el puntaje del jugador
            val sharedPreferences = requireActivity().getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)
            val nombreJugador = sharedPreferences.getString("nombre_usuario", "") ?: "jugador_desconocido"
            dataManager.guardarPuntaje(nombreJugador, puntaje)

            findNavController().navigate(R.id.action_juegoFragment_to_puntajesFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}