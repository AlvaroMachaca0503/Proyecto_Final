// Creador: Enyelbert Anderson Panta Huaracha
// Fecha de creación: 11 de diciembre de 2024
// Descripción: Fragmento que muestra una pregunta y sus opciones de respuesta al usuario.

package com.example.juegopreguntas

data class Pregunta(
    val texto: String,
    val opcion1: String,
    val opcion2: String,
    val opcion3: String,
    val opcion4: String,
    val respuestaCorrecta: Int // Índice de la respuesta correcta
)


