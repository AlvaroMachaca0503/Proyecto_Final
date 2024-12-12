// Creador: Alvaro Andre Machaca Melendez
// Fecha de creación: 11 de diciembre de 2024
// Descripción: Clase para gestionar los datos de la aplicación.

package com.example.juegopreguntas

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataManager(private val context: Context) {

    // SharedPreferences para almacenar los datos.
    private val sharedPreferences = context.getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)
    // Gson para serializar/deserializar objetos a JSON.
    private val gson = Gson()

    // --- Usuarios ---

    // Guarda un objeto Usuario en SharedPreferences.
    fun guardarUsuario(usuario: Usuario) {
        val usuarioJson = gson.toJson(usuario)
        val editor = sharedPreferences.edit()
        editor.putString("usuario", usuarioJson)
        editor.putBoolean("sesion_iniciada", true)
        editor.apply()
    }

    // Obtiene un objeto Usuario de SharedPreferences.
    fun obtenerUsuario(nombreUsuario: String): Usuario? {
        val usuarioJson = sharedPreferences.getString("usuario", null)
        return if (usuarioJson != null) {
            gson.fromJson(usuarioJson, Usuario::class.java)
        } else {
            null
        }
    }

    // Verifica si hay una sesión iniciada.
    fun obtenerSesionIniciada(): Boolean {
        return sharedPreferences.getBoolean("sesion_iniciada", false)
    }

    // --- Categorías y preguntas ---

    // Guarda una lista de categorías en SharedPreferences.
    fun guardarCategorias(categorias: List<Categoria>) {
        val categoriasJson = gson.toJson(categorias)
        val editor = sharedPreferences.edit()
        editor.putString("categorias_json", categoriasJson)
        editor.apply()
    }

    // Obtiene la lista de categorías de SharedPreferences.
    fun obtenerCategorias(): List<Categoria> {
        val json = sharedPreferences.getString("categorias_json", null)
        val type = object : TypeToken<List<Categoria>>() {}.type
        return gson.fromJson(json, type) ?: listOf()
    }

    // Inicializa las categorías si no existen.
    fun inicializarCategorias() {
        if (obtenerCategorias().isEmpty()) {
            // Crea una lista de categorías con preguntas de ejemplo.
            val categorias = listOf(
                Categoria("Historia", listOf(
                    Pregunta("¿En qué año comenzó la Primera Guerra Mundial?", "1914", "1917", "1939", "1945", 0),
                    // ... más preguntas de historia
                )),
                // ... más categorías (Geografía, Ciencia, Entretenimiento)
            )
            guardarCategorias(categorias)
        }
    }

    // --- Puntajes ---

    // Guarda el puntaje de un usuario.
    fun guardarPuntaje(nombreUsuario: String, puntaje: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt("puntaje_maximo_$nombreUsuario", puntaje)
        editor.apply()
    }

    // Obtiene la lista de puntajes (máximo 10).
    fun obtenerPuntajes(): List<Pair<String, Int>> {
        val puntajes = mutableListOf<Pair<String, Int>>()

        // Añadir datos ficticios.
        puntajes.add(Pair("Usuario1", 120))
        // ... más datos ficticios

        // Obtener los puntajes reales.
        for (key in sharedPreferences.all.keys) {
            if (key.startsWith("puntaje_maximo_")) {
                val nombreUsuario = key.substringAfter("puntaje_maximo_")
                val puntaje = sharedPreferences.getInt(key, 0)
                puntajes.add(Pair(nombreUsuario, puntaje))
            }
        }

        // Ordenar y devolver los 10 mejores puntajes.
        puntajes.sortByDescending { it.second }
        return puntajes.take(10)
    }

}
