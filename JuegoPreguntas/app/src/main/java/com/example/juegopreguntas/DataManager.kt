package com.example.juegopreguntas

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataManager(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)
    private val gson = Gson()


    fun guardarUsuario(usuario: Usuario) {
        val usuarioJson = gson.toJson(usuario)
        val editor = sharedPreferences.edit()
        editor.putString("usuario_${usuario.nombre}", usuarioJson)
        editor.apply()
    }

    fun obtenerUsuario(nombreUsuario: String): Usuario? {
        val usuarioJson = sharedPreferences.getString("usuario_$nombreUsuario", null)
        return if (usuarioJson != null) {
            gson.fromJson(usuarioJson, Usuario::class.java)
        } else {
            null
        }
    }

    // --- Categorías y preguntas ---

    fun guardarCategorias(categorias: List<Categoria>) {
        val categoriasJson = gson.toJson(categorias)
        val editor = sharedPreferences.edit()
        editor.putString("categorias_json", categoriasJson)
        editor.apply()
    }

    fun obtenerCategorias(): List<Categoria> {
        val json = sharedPreferences.getString("categorias_json", null)
        val type = object : TypeToken<List<Categoria>>() {}.type
        return gson.fromJson(json, type) ?: listOf()
    }
    fun inicializarCategorias() {
        if (obtenerCategorias().isEmpty()) {
            val categorias = listOf(
                Categoria("Historia", listOf(
                    Pregunta("¿En qué año comenzó la Primera Guerra Mundial?", "1914", "1917", "1939", "1945", 0),
                    // ... más preguntas de historia
                )),
                Categoria("Geografía", listOf(
                    Pregunta("¿Cuál es el país más grande del mundo?", "Rusia", "China", "Canadá", "Estados Unidos", 0),
                    // ... más preguntas de geografía
                )),
                Categoria("Ciencia", listOf(
                    Pregunta("¿Cuál es el símbolo químico del agua?", "H2O", "CO2", "NaCl", "O2", 0),
                    // ... más preguntas de ciencia
                )),
                Categoria("Entretenimiento", listOf(
                    Pregunta("¿Quién dirigió la película 'Titanic'?", "James Cameron", "Steven Spielberg", "Quentin Tarantino", "Christopher Nolan", 0),
                    // ... más preguntas de entretenimiento
                ))
            )
            guardarCategorias(categorias)
        }
    }
    // --- Puntajes ---

    fun guardarPuntaje(nombreUsuario: String, puntaje: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt("puntaje_maximo_$nombreUsuario", puntaje)
        editor.apply()
    }

    fun obtenerPuntaje(nombreUsuario: String): Int {
        return sharedPreferences.getInt("puntaje_maximo_$nombreUsuario", 0)
    }

    // ... otras funciones para guardar y recuperar datos
}