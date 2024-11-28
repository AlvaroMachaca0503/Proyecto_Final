package com.example.preguntados.utils

import com.example.preguntados.models.Pregunta

object PreguntaRepository {
    private val listaPreguntas = listOf(
        Pregunta(
            categoria = "Historia",
            pregunta = "¿Quién fue el primer presidente de los Estados Unidos?",
            opciones = listOf("George Washington", "Thomas Jefferson", "Abraham Lincoln", "John Adams"),
            respuestaCorrecta = "George Washington"
        ),
        Pregunta(
            categoria = "Ciencia",
            pregunta = "¿Cuál es el elemento químico con el símbolo O?",
            opciones = listOf("Oxígeno", "Oro", "Osmio", "Oxalato"),
            respuestaCorrecta = "Oxígeno"
        ),
        Pregunta(
            categoria = "Geografía",
            pregunta = "¿Cuál es la capital de Francia?",
            opciones = listOf("Roma", "Madrid", "París", "Berlín"),
            respuestaCorrecta = "París"
        ),
        Pregunta(
            categoria = "Deporte",
            pregunta = "¿Cuántos jugadores tiene un equipo de fútbol en el campo?",
            opciones = listOf("9", "10", "11", "12"),
            respuestaCorrecta = "11"
        ),
        Pregunta(
            categoria = "Arte",
            pregunta = "¿Quién pintó la Mona Lisa?",
            opciones = listOf("Van Gogh", "Da Vinci", "Picasso", "Rembrandt"),
            respuestaCorrecta = "Da Vinci"
        ),
        Pregunta(
            categoria = "Entretenimiento",
            pregunta = "¿Cómo se llama el villano principal en Avengers?",
            opciones = listOf("Ultron", "Loki", "Thanos", "Galactus"),
            respuestaCorrecta = "Thanos"
        )
    )

    fun obtenerPreguntas(): List<Pregunta> {
        return listaPreguntas.shuffled()
    }
}
