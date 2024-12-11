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

class LoginFragment : Fragment() {

    private lateinit var usuarioEditText: EditText
    private lateinit var contrasenaEditText: EditText
    private lateinit var ingresarButton: Button
    private lateinit var crearCuentaButton: Button
    private lateinit var olvidarContrasenaButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        usuarioEditText = view.findViewById(R.id.usuarioEditText)
        contrasenaEditText = view.findViewById(R.id.contrasenaEditText)
        ingresarButton = view.findViewById(R.id.ingresarButton)
        crearCuentaButton = view.findViewById(R.id.crearCuentaButton)
        olvidarContrasenaButton = view.findViewById(R.id.olvidarContrasenaButton)

        ingresarButton.setOnClickListener {
            val usuario = usuarioEditText.text.toString()
            val contrasena = contrasenaEditText.text.toString()

            // TODO: Implementar lógica de autenticación (simulada aquí)
            if (usuario == "usuario" && contrasena == "contrasena") {
                findNavController().navigate(R.id.action_loginFragment_to_menuFragment)
            } else {
                Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        crearCuentaButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_crearCuentaFragment)
        }

        olvidarContrasenaButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_olvidarContrasenaFragment)
        }

        return view
    }
}