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
        editor.putString("usuario", usuarioJson) // Usar la clave "usuario"
        editor.putBoolean("sesion_iniciada", true)
        editor.apply()
    }

    fun obtenerUsuario(nombreUsuario: String): Usuario? {
        val usuarioJson = sharedPreferences.getString("usuario", null) // Usar la clave "usuario"
        return if (usuarioJson != null) {
            gson.fromJson(usuarioJson, Usuario::class.java)
        } else {
            null
        }
    }

    fun obtenerSesionIniciada(): Boolean {
        return sharedPreferences.getBoolean("sesion_iniciada", false)
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
                    Pregunta("¿Quién fue el primer presidente de los Estados Unidos?", "George Washington", "Abraham Lincoln", "Thomas Jefferson", "John Adams", 0),
                    Pregunta("¿Cuál fue la civilización que construyó Machu Picchu?", "Incas", "Mayas", "Aztecas", "Olmecas", 0),
                    Pregunta("¿Quién escribió la obra 'Hamlet'?", "William Shakespeare", "Miguel de Cervantes", "Charles Dickens", "Edgar Allan Poe", 0),
                    Pregunta("¿En qué país se encuentra la Torre Eiffel?", "Francia", "Italia", "España", "Alemania", 0)
                )),
                Categoria("Geografía", listOf(
                    Pregunta("¿Cuál es el país más grande del mundo?", "Rusia", "China", "Canadá", "Estados Unidos", 0),
                    Pregunta("¿Cuál es la capital de Australia?", "Sídney", "Melbourne", "Canberra", "Perth", 2),
                    Pregunta("¿En qué continente se encuentra el desierto del Sahara?", "África", "Asia", "América del Norte", "América del Sur", 0),
                    Pregunta("¿Cuál es el océano más grande de la Tierra?", "Pacífico", "Atlántico", "Índico", "Ártico", 0),
                    Pregunta("¿Cuál es el río más largo del mundo?", "Amazonas", "Nilo", "Misisipi", "Yangtsé", 1)
                )),
                Categoria("Ciencia", listOf(
                    Pregunta("¿Cuál es el símbolo químico del agua?", "H2O", "CO2", "NaCl", "O2", 0),
                    Pregunta("¿Quién formuló la teoría de la relatividad?", "Albert Einstein", "Isaac Newton", "Galileo Galilei", "Stephen Hawking", 0),
                    Pregunta("¿Cuál es el planeta más cercano al Sol?", "Mercurio", "Venus", "Tierra", "Marte", 0),
                    Pregunta("¿Cuántos huesos tiene el cuerpo humano adulto?", "206", "180", "212", "196", 0),
                    Pregunta("¿Cuál es el elemento químico más abundante en el universo?", "Hidrógeno", "Oxígeno", "Carbono", "Helio", 0)
                )),
                Categoria("Entretenimiento", listOf(
                    Pregunta("¿Quién dirigió la película 'Titanic'?", "James Cameron", "Steven Spielberg", "Quentin Tarantino", "Christopher Nolan", 0),
                    Pregunta("¿Cuál es el nombre del personaje principal de la saga de videojuegos 'The Legend of Zelda'?", "Link", "Mario", "Sonic", "Pikachu", 0),
                    Pregunta("¿Quién escribió la serie de libros 'Harry Potter'?", "J.K. Rowling", "Stephen King", "George R. R. Martin", "J.R.R. Tolkien", 0),
                    Pregunta("¿Cuál es la banda de rock que compuso la canción 'Bohemian Rhapsody'?", "Queen", "The Beatles", "Led Zeppelin", "Pink Floyd", 0),
                    Pregunta("¿En qué ciudad se encuentra el famoso museo del Louvre?", "París", "Londres", "Roma", "Madrid", 0)
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


    fun obtenerPuntajes(): List<Pair<String, Int>> {
        val puntajes = mutableListOf<Pair<String, Int>>()

        // Añadir datos ficticios de ejemplo
        puntajes.add(Pair("Usuario1", 120))
        puntajes.add(Pair("Usuario2", 95))
        puntajes.add(Pair("Usuario3", 80))
        puntajes.add(Pair("Usuario4", 75))
        puntajes.add(Pair("Usuario5", 70))
        puntajes.add(Pair("Usuario6", 65))
        puntajes.add(Pair("Usuario7", 60))
        puntajes.add(Pair("Usuario8", 55))
        puntajes.add(Pair("Usuario9", 50))
        puntajes.add(Pair("Usuario10", 45))

        // Obtener los puntajes reales de SharedPreferences
        for (key in sharedPreferences.all.keys) {
            if (key.startsWith("puntaje_maximo_")) {
                val nombreUsuario = key.substringAfter("puntaje_maximo_")
                val puntaje = sharedPreferences.getInt(key, 0)
                puntajes.add(Pair(nombreUsuario, puntaje))
            }
        }

        // Ordenar la lista por puntaje (descendente)
        puntajes.sortByDescending { it.second }

        // Devolver solo los 10 mejores puntajes
        return puntajes.take(10)
    }

    // ... otras funciones para guardar y recuperar datos
}