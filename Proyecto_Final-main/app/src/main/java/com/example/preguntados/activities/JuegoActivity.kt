package com.example.preguntados.activities

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.preguntados.R
import com.example.preguntados.models.Juego

class JuegoActivity : AppCompatActivity() {

    private lateinit var juego: Juego
    private lateinit var preguntaTextView: TextView
    private lateinit var categoriaTextView: TextView
    private lateinit var respuestaTextView: TextView
    private lateinit var opcion1Button: Button
    private lateinit var opcion2Button: Button
    private lateinit var opcion3Button: Button
    private lateinit var opcion4Button: Button
    private lateinit var reiniciarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)

        // Inicialización de vistas
        preguntaTextView = findViewById(R.id.preguntaTextView)
        categoriaTextView = findViewById(R.id.categoriaTextView)
        respuestaTextView = findViewById(R.id.respuestaTextView)
        opcion1Button = findViewById(R.id.opcion1)
        opcion2Button = findViewById(R.id.opcion2)
        opcion3Button = findViewById(R.id.opcion3)
        opcion4Button = findViewById(R.id.opcion4)
        reiniciarButton = findViewById(R.id.reiniciarButton)

        // Inicialización del objeto Juego
        juego = Juego()

        // Configuración de la ruleta para elegir la categoría
        girarRuleta()

        // Configuración de los botones de opciones para verificar las respuestas
        opcion1Button.setOnClickListener { verificarRespuesta(opcion1Button.text.toString()) }
        opcion2Button.setOnClickListener { verificarRespuesta(opcion2Button.text.toString()) }
        opcion3Button.setOnClickListener { verificarRespuesta(opcion3Button.text.toString()) }
        opcion4Button.setOnClickListener { verificarRespuesta(opcion4Button.text.toString()) }

        // Configuración del botón de reiniciar el juego
        reiniciarButton.setOnClickListener { reiniciarJuego() }
    }

    // Método para girar la ruleta y elegir una categoría aleatoria
    private fun girarRuleta() {
        val categoria = juego.obtenerCategoriaAleatoria()
        categoriaTextView.text = "Categoría: $categoria"
        juego.configurarPreguntasPorCategoria(categoria)
        mostrarPregunta()
    }

    // Mostrar la pregunta actual en el juego
    private fun mostrarPregunta() {
        val pregunta = juego.obtenerPreguntaActual()
        if (pregunta != null) {
            preguntaTextView.text = pregunta.pregunta
            // Asignar las opciones de respuesta
            opcion1Button.text = pregunta.opciones[0]
            opcion2Button.text = pregunta.opciones[1]
            opcion3Button.text = pregunta.opciones[2]
            opcion4Button.text = pregunta.opciones[3]
        } else {
            // Si no hay más preguntas, finalizar el juego
            finDelJuego()
        }
    }

    // Verificar si la respuesta seleccionada es correcta o incorrecta
    private fun verificarRespuesta(opcion: String) {
        val esCorrecta = juego.verificarRespuesta(opcion)

        // Cambiar el color de fondo dependiendo de si la respuesta es correcta o incorrecta
        val color = if (esCorrecta) Color.GREEN else Color.RED
        window.decorView.setBackgroundColor(color)

        // Mostrar mensaje de respuesta
        respuestaTextView.text = if (esCorrecta) "¡Correcto!" else "Incorrecto"

        // Mostrar la siguiente pregunta o finalizar el juego
        if (!juego.juegoTerminado()) {
            mostrarPregunta()
        } else {
            finDelJuego()
        }
    }

    // Método para finalizar el juego y mostrar el puntaje final
    private fun finDelJuego() {
        respuestaTextView.text = "Juego Terminado\nPuntaje Final: ${juego.obtenerPuntaje()}"
        reiniciarButton.visibility = Button.VISIBLE  // Hacer visible el botón de reinicio
    }

    // Método para reiniciar el juego
    private fun reiniciarJuego() {
        // Reiniciar el estado del juego
        juego.reiniciarJuego()
        respuestaTextView.text = "" // Limpiar los mensajes anteriores
        reiniciarButton.visibility = Button.INVISIBLE  // Ocultar el botón de reinicio

        // Configurar nuevamente el juego
        girarRuleta()
        window.decorView.setBackgroundColor(Color.WHITE)  // Volver al color de fondo original
    }
}
