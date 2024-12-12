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

    private lateinit var correoEditText: EditText
    private lateinit var enviarButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_olvidar_contrasena, container, false)

        correoEditText = view.findViewById(R.id.correoEditText)
        enviarButton = view.findViewById(R.id.enviarButton)

        enviarButton.setOnClickListener {
            val correo = correoEditText.text.toString()

            if (correo.isBlank()) {
                Toast.makeText(context, "Por favor, ingrese su correo electrónico", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(context, "Se ha enviado un correo a $correo para recuperar su contraseña", Toast.LENGTH_SHORT).show()

            }
        }

        return view
    }
}