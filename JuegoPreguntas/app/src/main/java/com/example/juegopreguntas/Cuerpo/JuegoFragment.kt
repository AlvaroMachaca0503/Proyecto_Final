package com.example.juegopreguntas.Cuerpo

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.juegopreguntas.Preguntas
import com.example.juegopreguntas.R

class JuegoFragment : Fragment() {

    private lateinit var preguntaTextView: TextView
    private lateinit var opcion1RadioButton: RadioButton
    private lateinit var opcion2RadioButton: RadioButton
    private lateinit var opcion3RadioButton: RadioButton
    private lateinit var opcion4RadioButton: RadioButton
    private lateinit var siguienteButton: Button

    private val preguntas = listOf(
        Preguntas("¿Cuál es la capital de Francia?", "Berlín", "París", "Madrid", "Roma", 2),
        Preguntas("¿Quién pintó la Mona Lisa?", "Picasso", "Dalí", "Miguel Ángel", "Da Vinci", 4),
        Preguntas("¿Cuál es el río más largo del mundo?", "Amazonas", "Nilo", "Misisipi", "Yangtsé", 2),
        // ... (Agregar más preguntas)
    )
    private var preguntaActual = preguntas[0]
    private var indicePregunta = 0
    private var puntaje = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_juego, container, false)

        preguntaTextView = view.findViewById(R.id.preguntaTextView)
        opcion1RadioButton = view.findViewById(R.id.opcion1RadioButton)
        opcion2RadioButton = view.findViewById(R.id.opcion2RadioButton)
        opcion3RadioButton = view.findViewById(R.id.opcion3RadioButton)
        opcion4RadioButton = view.findViewById(R.id.opcion4RadioButton)
        siguienteButton = view.findViewById(R.id.siguienteButton)

        mostrarPregunta()

        siguienteButton.setOnClickListener {
            val idRadioButtonSeleccionado = when {
                opcion1RadioButton.isChecked -> R.id.opcion1RadioButton
                opcion2RadioButton.isChecked -> R.id.opcion2RadioButton
                opcion3RadioButton.isChecked -> R.id.opcion3RadioButton
                opcion4RadioButton.isChecked -> R.id.opcion4RadioButton
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
        preguntaTextView.text = preguntaActual.texto
        opcion1RadioButton.text = preguntaActual.opcion1
        opcion2RadioButton.text = preguntaActual.opcion2
        opcion3RadioButton.text = preguntaActual.opcion3
        opcion4RadioButton.text = preguntaActual.opcion4

        // Reiniciar colores de las opciones
        opcion1RadioButton.setTextColor(Color.BLACK)
        opcion2RadioButton.setTextColor(Color.BLACK)
        opcion3RadioButton.setTextColor(Color.BLACK)
        opcion4RadioButton.setTextColor(Color.BLACK)
    }

    private fun verificarRespuesta(idRadioButtonSeleccionado: Int) {
        val respuestaCorrecta = when (preguntaActual.respuestaCorrecta) {
            1 -> R.id.opcion1RadioButton
            2 -> R.id.opcion2RadioButton
            3 -> R.id.opcion3RadioButton
            4 -> R.id.opcion4RadioButton
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
        if (indicePregunta < preguntas.size) {
            preguntaActual = preguntas[indicePregunta]
            mostrarPregunta()
        } else {
            // Navegar al PuntajesFragment y pasar el puntaje
            val bundle = Bundle()
            bundle.putInt("puntaje", puntaje)
            findNavController().navigate(R.id.action_juegoFragment_to_puntajesFragment, bundle)
        }
    }
}