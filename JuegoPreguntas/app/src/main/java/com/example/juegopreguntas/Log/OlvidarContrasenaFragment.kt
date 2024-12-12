// Creador: Alvaro Andre Machaca Melendez
// Fecha de creación: 11 de diciembre de 2024
// Descripción: Fragmento para recuperar la contraseña.

package com.example.juegopreguntas.Log

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.juegopreguntas.R

class OlvidarContrasenaFragment : Fragment() {

    private lateinit var correoEditText: EditText // Variable para el EditText del correo.
    private lateinit var enviarButton: Button // Variable para el botón "Enviar".

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento.
        val view = inflater.inflate(R.layout.fragment_olvidar_contrasena, container, false)

        // Obtener las referencias al EditText y al Button.
        correoEditText = view.findViewById(R.id.correoEditText)
        enviarButton = view.findViewById(R.id.enviarButton)

        // Configurar el listener del botón "Enviar".
        enviarButton.setOnClickListener {
            // Obtener el correo electrónico ingresado.
            val correo = correoEditText.text.toString()

            // Validar que el correo no esté vacío.
            if (correo.isBlank()) {
                // Mostrar un mensaje de error si el campo está vacío.
                Toast.makeText(context, "Por favor, ingrese su correo electrónico", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Se ha enviado un correo a $correo para recuperar su contraseña", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
