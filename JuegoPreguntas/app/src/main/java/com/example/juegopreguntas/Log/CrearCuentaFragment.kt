// Creador: Enyelbert Anderson Panta Huaracha
// Fecha de creación: 11 de diciembre de 2024
// Descripción: Fragmento para crear una nueva cuenta de usuario.

package com.example.juegopreguntas.Log

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.juegopreguntas.DataManager
import com.example.juegopreguntas.R
import com.example.juegopreguntas.Usuario
import com.example.juegopreguntas.databinding.FragmentCrearCuentaBinding

class CrearCuentaFragment : Fragment() {

    private var _binding: FragmentCrearCuentaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar el layout.
        _binding = FragmentCrearCuentaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el listener del botón "Crear Cuenta".
        binding.crearCuentaButton.setOnClickListener {
            // Obtener los valores de los campos de entrada.
            val nombre = binding.nombreEditText.text.toString()
            val usuario = binding.usuarioEditText.text.toString()
            val contrasena = binding.contrasenaEditText.text.toString()
            val confirmarContrasena = binding.confirmarContrasenaEditText.text.toString()

            // Validar los campos de entrada.
            if (nombre.isBlank() || usuario.isBlank() || contrasena.isBlank() || confirmarContrasena.isBlank()) {
                Toast.makeText(context, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            } else if (contrasena != confirmarContrasena) {
                Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            } else {
                // Encriptar la contraseña (implementar la función encriptarContrasena()).
                val contrasenaEncriptada = encriptarContrasena(contrasena)

                // Crear un objeto Usuario.
                val usuarioObj = Usuario(usuario, contrasenaEncriptada)

                // Obtener una instancia de DataManager.
                val dataManager = DataManager(requireContext())

                // Guardar el usuario.
                dataManager.guardarUsuario(usuarioObj)

                // Mostrar un mensaje de éxito y navegar al fragmento de login.
                Toast.makeText(context, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_crearCuentaFragment_to_loginFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpiar la referencia al binding.
    }

    // Función para encriptar la contraseña (debe ser implementada).
    private fun encriptarContrasena(contrasena: String): String {
        // ... (tu código para encriptar la contraseña)
        // Por ahora, solo devolvemos la contraseña sin encriptar.
        return contrasena
    }
}