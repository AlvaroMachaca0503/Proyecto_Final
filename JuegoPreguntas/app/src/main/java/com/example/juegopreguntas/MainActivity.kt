// Creador: Enyelbert Anderson Panta Huaracha
// Fecha de creación: 11 de diciembre de 2024
// Descripción: Actividad principal de la aplicación.

package com.example.juegopreguntas

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer // Para la música de fondo.
    private lateinit var dataManager: DataManager // Para el manejo de datos.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Establecer el layout principal.

        dataManager = DataManager(this) // Inicializar el DataManager.
        dataManager.inicializarCategorias() // Inicializar las categorías de preguntas.

        // Obtener SharedPreferences.
        val sharedPreferences = getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)

        // Verificar si hay una sesión iniciada.
        if (dataManager.obtenerSesionIniciada()) {
            // Obtener el nombre de usuario.
            val nombreUsuario = sharedPreferences.getString("nombre_usuario", "") ?: ""
            // Obtener el usuario del DataManager.
            val usuario = dataManager.obtenerUsuario(nombreUsuario)
            if (usuario != null) {
                // Iniciar sesión automáticamente y navegar al MenuFragment.
                val navController = findNavController(R.id.nav_host_fragment)
                navController.navigate(R.id.loginFragment) // Debería navegar al MenuFragment
            } else {
                // Navegar al LoginFragment si no se encuentra el usuario.
                val navController = findNavController(R.id.nav_host_fragment)
                navController.navigate(R.id.loginFragment)
            }
        } else {
            // Navegar al LoginFragment si no hay sesión iniciada.
            val navController = findNavController(R.id.nav_host_fragment)
            navController.navigate(R.id.loginFragment)
        }

        // Reproducir música de fondo.
        mediaPlayer = MediaPlayer.create(this, R.raw.musica_fondo)
        mediaPlayer.start()

        // Establecer la imagen de fondo.
        window.decorView.setBackgroundResource(R.drawable.fondo_pantalla)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Detener y liberar el MediaPlayer.
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}