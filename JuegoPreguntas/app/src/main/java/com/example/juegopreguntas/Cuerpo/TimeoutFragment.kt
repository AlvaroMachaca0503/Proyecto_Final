// Creador: Andrea del Rosario Velazco Yana
// Fecha de creación: 12 de diciembre de 2024
// Descripción: Fragmento que muestra un mensaje de "¡SE ACABÓ EL TIEMPO!" y un botón para continuar.

package com.example.juegopreguntas.Cuerpo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.juegopreguntas.R

class TimeoutFragment : Fragment(R.layout.fragment_timeout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Muestra el texto "¡SE ACABÓ EL TIEMPO!" en la pantalla.
        val resultTextView: TextView = view.findViewById(R.id.resultTextView)
        resultTextView.text = "¡SE ACABÓ EL TIEMPO!"

        // Configura el botón "Siguiente Pregunta" para navegar al fragmento "JuegoFragment".
        val siguientePreguntaButton: Button = view.findViewById(R.id.siguientePreguntaButton)
        siguientePreguntaButton.setOnClickListener {
        }
    }
}
