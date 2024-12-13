// Creador: Andrea del Rosario Velazco Yana
// Fecha de creación: 11 de diciembre de 2024
// Descripción: Fragmento que muestra un mensaje de "¡CORRECTO!" y un botón para continuar.

package com.example.juegopreguntas.Cuerpo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.juegopreguntas.R
import androidx.fragment.app.activityViewModels


class CorrectFragment : Fragment(R.layout.fragment_correct) {

    private val viewModel: GameViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Muestra el texto "¡CORRECTO!" en la pantalla.
        val resultTextView: TextView = view.findViewById(R.id.resultTextView)
        resultTextView.text = "¡CORRECTO!"

        val correctImageView: ImageView = view.findViewById(R.id.correctImageView)
        correctImageView.setImageResource(R.drawable.check_correcto)


        // Configura el botón "Siguiente Pregunta" para que al presionarlo,
        // la aplicación navegue al fragmento "JuegoFragment".
        val siguientePreguntaButton: Button = view.findViewById(R.id.siguientePreguntaButton)
        siguientePreguntaButton.setOnClickListener {
            viewModel.avanzarPregunta()  // Avanza a la siguiente pregunta
            findNavController().navigate(R.id.action_correctFragment_to_juegoFragment)  // O `to_incorrectFragment`, dependiendo del fragmento
        }

    }
}
