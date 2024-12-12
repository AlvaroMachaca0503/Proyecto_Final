// Creador: Andrea del Rosario Velazco Yana
// Fecha de creación: 11 de diciembre de 2024
// Descripción: Fragmento que muestra un mensaje de "¡CORRECTO!" y un botón para continuar.

package com.example.juegopreguntas.Cuerpo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.juegopreguntas.R

class CorrectFragment : Fragment(R.layout.fragment_correct) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Muestra el texto "¡CORRECTO!" en la pantalla.
        val resultTextView: TextView = view.findViewById(R.id.resultTextView)
        resultTextView.text = "¡CORRECTO!"

        // Configura el botón "Siguiente Pregunta" para que al presionarlo,
        // la aplicación navegue al fragmento "JuegoFragment".
        val siguientePreguntaButton: Button = view.findViewById(R.id.siguientePreguntaButton)
        siguientePreguntaButton.setOnClickListener {
            findNavController().navigate(R.id.action_correctFragment_to_juegoFragment)
        }
    }
}
