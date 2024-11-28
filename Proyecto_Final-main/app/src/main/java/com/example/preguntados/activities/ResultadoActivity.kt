package com.example.preguntados.activities


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.preguntados.R

class ResultadoActivity : AppCompatActivity() {

    private lateinit var resultadoTextView: TextView
    private lateinit var botonIntentarDeNuevo: Button
    private lateinit var botonContinuar: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        // Inicializar las vistas
        resultadoTextView = findViewById(R.id.resultadoTextView)
        botonIntentarDeNuevo = findViewById(R.id.botonIntentarDeNuevo)
        botonContinuar = findViewById(R.id.botonContinuar)

        // Obtener el resultado desde el Intent
        val resultado = intent.getStringExtra("resultado")

        // Mostrar el resultado en el TextView
        if (resultado == "correcto") {
            resultadoTextView.text = "¡Correcto!"
        } else {
            resultadoTextView.text = "Respuesta Incorrecta 😞"
        }

        // Configurar el botón "Intentar de Nuevo"
        botonIntentarDeNuevo.setOnClickListener {
            // Regresar a la actividad de juego
            val intent = Intent(this, JuegoActivity::class.java)
            startActivity(intent)
            finish() // Finalizar esta actividad para evitar que el usuario regrese a ella con el botón de retroceso
        }

        // Configurar el botón "Continuar"
        botonContinuar.setOnClickListener {
            // Aquí podrías pasar a la siguiente pregunta o actividad si la tienes implementada
            val intent = Intent(this, SiguienteActivity::class.java) // Cambia a la actividad adecuada
            startActivity(intent)
            finish() // Finalizar esta actividad
        }
    }

    class SiguienteActivity {

    }
}
