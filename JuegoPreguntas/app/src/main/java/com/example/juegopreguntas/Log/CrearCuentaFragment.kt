package com.example.juegopreguntas.Log

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.juegopreguntas.R

class CrearCuentaFragment : Fragment() {

    private lateinit var nombreEditText: EditText
    private lateinit var usuarioEditText: EditText
    private lateinit var contrasenaEditText: EditText
    private lateinit var confirmarContrasenaEditText: EditText
    private lateinit var crearCuentaButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crear_cuenta, container, false)

        nombreEditText = view.findViewById(R.id.nombreEditText)
        usuarioEditText = view.findViewById(R.id.usuarioEditText)
        contrasenaEditText = view.findViewById(R.id.contrasenaEditText)
        confirmarContrasenaEditText = view.findViewById(R.id.confirmarContrasenaEditText)
        crearCuentaButton = view.findViewById(R.id.crearCuentaButton)

        crearCuentaButton.setOnClickListener {
            val nombre = nombreEditText.text.toString()
            val usuario = usuarioEditText.text.toString()
            val contrasena = contrasenaEditText.text.toString()
            val confirmarContrasena = confirmarContrasenaEditText.text.toString()

            if (nombre.isBlank() || usuario.isBlank() || contrasena.isBlank() || confirmarContrasena.isBlank()) {
                Toast.makeText(context, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            } else if (contrasena != confirmarContrasena) {
                Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(context, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_crearCuentaFragment_to_loginFragment)
            }
        }

        return view
    }
}