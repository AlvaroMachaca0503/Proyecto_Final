package com.example.preguntados.utils

object PuntajesManager {
    private val puntajes = mutableListOf<Int>()

    fun agregarPuntaje(puntaje: Int) {
        puntajes.add(puntaje)
    }

    fun obtenerPuntajes(): List<Int> {
        return puntajes
    }
}
