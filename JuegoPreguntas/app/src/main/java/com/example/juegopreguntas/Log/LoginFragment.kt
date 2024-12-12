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
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ingresarButton.setOnClickListener {
            val usuario = binding.usuarioEditText.text.toString()
            val contrasena = binding.contrasenaEditText.text.toString()

            val dataManager = DataManager(requireContext())
            val usuarioGuardado = dataManager.obtenerUsuario(usuario)

            if (usuarioGuardado != null && usuarioGuardado.contrasena == encriptarContrasena(contrasena)) {
                // Iniciar sesión exitosamente
                findNavController().navigate(R.id.action_loginFragment_to_menuFragment)
            } else {
                Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.crearCuentaButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_crearCuentaFragment)
        }

        binding.olvidarContrasenaButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_olvidarContrasenaFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Debes implementar esta función para encriptar la contraseña
    private fun encriptarContrasena(contrasena: String): String {
        // ... (tu código para encriptar la contraseña)
        // Por ahora, solo devolvemos la contraseña sin encriptar
        return contrasena
    }
}