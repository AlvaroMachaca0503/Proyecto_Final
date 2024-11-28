package com.example.preguntados.models

class Ruleta(private val categorias: List<String>) {
    fun girarRuleta(): String {
        return categorias.random()
    }
}
