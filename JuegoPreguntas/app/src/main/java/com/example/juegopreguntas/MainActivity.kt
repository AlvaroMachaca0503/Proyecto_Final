package com.example.juegopreguntas

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var dataManager: DataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataManager = DataManager(this)
        dataManager.inicializarCategorias()

        // Obtener una instancia de SharedPreferences
        val sharedPreferences = getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)

        // Verificar si hay una sesión iniciada
        if (dataManager.obtenerSesionIniciada()) {
            // Obtener el usuario de SharedPreferences
            val nombreUsuario = sharedPreferences.getString("nombre_usuario", "") ?: "" // Si es null, usar una cadena vacía
            val usuario = dataManager.obtenerUsuario(nombreUsuario)
            if (usuario != null) {
                // Iniciar sesión automáticamente con el usuario
                // Navegar al MenuFragment
                val navController = findNavController(R.id.nav_host_fragment)
                navController.navigate(R.id.loginFragment)
            } else {
                // No se encontró un usuario válido, navegar al LoginFragment
                val navController = findNavController(R.id.nav_host_fragment)
                navController.navigate(R.id.loginFragment)
            }
        } else {
            // Mostrar la interfaz de registro o inicio de sesión
            // Navegar al LoginFragment
            val navController = findNavController(R.id.nav_host_fragment)
            navController.navigate(R.id.loginFragment)
        }

        // Reproducir música de fondo
        mediaPlayer = MediaPlayer.create(this, R.raw.musica_fondo)
        mediaPlayer.start()

        window.decorView.setBackgroundResource(R.drawable.fondo_pantalla)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}