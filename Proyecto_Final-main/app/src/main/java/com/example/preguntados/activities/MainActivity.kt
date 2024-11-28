package com.example.preguntados.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.preguntados.R

class MainActivity : AppCompatActivity() {

    private lateinit var logoImageView: ImageView
    private lateinit var botonJugar: Button
    private lateinit var botonPuntajes: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar las vistas
        logoImageView = findViewById(R.id.logoImageView)
        botonJugar = findViewById(R.id.botonJugar)
        botonPuntajes = findViewById(R.id.botonPuntajes)

        // Configurar el botón para iniciar el juego
        botonJugar.setOnClickListener {
            Log.d("MainActivity", "Botón Jugar presionado")
            val intent = Intent(this, JuegoActivity::class.java)
            startActivity(intent)
        }

        // Configurar el botón para ver los puntajes
        botonPuntajes.setOnClickListener {
            Log.d("MainActivity", "Botón Puntajes presionado")
            val intent = Intent(this, PuntajesActivity::class.java)
            startActivity(intent)
        }
    }
}