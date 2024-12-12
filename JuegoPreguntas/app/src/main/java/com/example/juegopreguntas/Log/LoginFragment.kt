// Creador: Enyelbert Anderson Panta Huaracha
// Fecha de creación: 11 de diciembre de 2024
// Descripción: Fragmento para iniciar sesión.

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
import com.example.juegopreguntas.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar el layout.
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el listener del botón "Ingresar".
        binding.ingresarButton.setOnClickListener {
            // Obtener el usuario y la contraseña.
            val usuario = binding.usuarioEditText.text.toString()
            val contrasena = binding.contrasenaEditText.text.toString()

            // Obtener el usuario guardado del DataManager.
            val dataManager = DataManager(requireContext())
            val usuarioGuardado = dataManager.obtenerUsuario(usuario)

            // Verificar si el usuario y la contraseña son correctos.
            if (usuarioGuardado != null && usuarioGuardado.contrasena == encriptarContrasena(contrasena)) {
                // Iniciar sesión y navegar al menú.
                findNavController().navigate(R.id.action_loginFragment_to_menuFragment)
            } else {
                // Mostrar un mensaje de error.
                Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        // Configurar el listener del botón "Crear Cuenta".
        binding.crearCuentaButton.setOnClickListener {
            // Navegar al fragmento para crear una cuenta.
            findNavController().navigate(R.id.action_loginFragment_to_crearCuentaFragment)
        }

        // Configurar el listener del botón "Olvidar Contraseña".
        binding.olvidarContrasenaButton.setOnClickListener {
            // Navegar al fragmento para recuperar la contraseña.
            findNavController().navigate(R.id.action_loginFragment_to_olvidarContrasenaFragment)
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