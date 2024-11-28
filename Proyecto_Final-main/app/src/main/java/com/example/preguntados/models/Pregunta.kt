package com.example.preguntados.models

data class Pregunta(
    val categoria: String,
    val pregunta: String,
    val opciones: List<String>,
    val respuestaCorrecta: String
)