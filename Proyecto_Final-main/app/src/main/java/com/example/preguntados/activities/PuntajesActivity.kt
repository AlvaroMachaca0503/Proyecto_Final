package com.example.preguntados.activities

import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.preguntados.R
import com.example.preguntados.utils.PuntajesManager

class PuntajesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntajes)

        // Obtener la lista de puntajes
        val puntajes = PuntajesManager.obtenerPuntajes()

        // Mostrar los puntajes dinámicamente
        val scoreList = findViewById<LinearLayout>(R.id.scoreList)
        puntajes.forEach { puntaje ->
            val puntajeView = TextView(this).apply {
                text = puntaje.toString()
                textSize = 20f
                setPadding(16, 16, 16, 16)
                setTextColor(resources.getColor(R.color.white, null))
                gravity = android.view.Gravity.CENTER
            }
            scoreList.addView(puntajeView)
        }

        // Configurar el botón de retroceso
        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
    }
}
