package com.example.juegopreguntas

data class Preguntas(
    val texto: String,
    val opcion1: String,
    val opcion2: String,
    val opcion3: String,
    val opcion4: String,
    val respuestaCorrecta: Int // Índice de la respuesta correcta (1-4)
)