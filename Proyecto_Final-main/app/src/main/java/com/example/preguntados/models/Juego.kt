package com.example.preguntados.models

// Clase para representar una pregunta
data class Preguntas(
    val categoria: String,
    val pregunta: String,
    val opciones: List<String>,
    val respuestaCorrecta: String
)

// Repositorio de preguntas, donde podemos obtener las preguntas para el juego
object PreguntaRepository {
    fun obtenerPreguntas(): List<Pregunta> {
        return listOf(
            // Cultura
            Pregunta("Cultura", "¿Quién pintó la Mona Lisa?", listOf("Da Vinci", "Picasso", "Van Gogh", "Rembrandt"), "Da Vinci"),
            Pregunta("Cultura", "¿En qué país nació el pintor Pablo Picasso?", listOf("Francia", "España", "Italia", "Portugal"), "España"),
            Pregunta("Cultura", "¿Quién escribió 'Don Quijote de la Mancha'?", listOf("Gabriel García Márquez", "Miguel de Cervantes", "Jorge Luis Borges", "Pablo Neruda"), "Miguel de Cervantes"),
            Pregunta("Cultura", "¿Cuál es el nombre del famoso escritor de 'Harry Potter'?", listOf("J.K. Rowling", "Stephen King", "George R.R. Martin", "J.R.R. Tolkien"), "J.K. Rowling"),
            Pregunta("Cultura", "¿Qué instrumento musical es característico de la cultura flamenca?", listOf("Guitarra", "Piano", "Violín", "Bajo"), "Guitarra"),

            // Ciencia
            Pregunta("Ciencia", "¿Cuál es el planeta más grande del sistema solar?", listOf("Júpiter", "Saturno", "Tierra", "Marte"), "Júpiter"),
            Pregunta("Ciencia", "¿Qué gas es necesario para la respiración humana?", listOf("Oxígeno", "Hidrógeno", "Dióxido de carbono", "Nitrógeno"), "Oxígeno"),
            Pregunta("Ciencia", "¿Cuál es el órgano más grande del cuerpo humano?", listOf("El corazón", "La piel", "El cerebro", "El hígado"), "La piel"),
            Pregunta("Ciencia", "¿Cuántos huesos tiene el cuerpo humano adulto?", listOf("206", "215", "210", "220"), "206"),
            Pregunta("Ciencia", "¿Cuál es la fórmula química del agua?", listOf("H2O", "CO2", "O2", "H2SO4"), "H2O"),

            // Historia
            Pregunta("Historia", "¿Quién fue el primer presidente de los Estados Unidos?", listOf("George Washington", "Abraham Lincoln", "Thomas Jefferson", "Franklin Roosevelt"), "George Washington"),
            Pregunta("Historia", "¿En qué año comenzó la Segunda Guerra Mundial?", listOf("1914", "1939", "1941", "1945"), "1939"),
            Pregunta("Historia", "¿Quién fue el líder de la Revolución Francesa?", listOf("Napoleón Bonaparte", "Maximilien Robespierre", "Luis XVI", "Jean-Jacques Rousseau"), "Maximilien Robespierre"),
            Pregunta("Historia", "¿En qué año se firmó la Declaración de Independencia de los Estados Unidos?", listOf("1776", "1789", "1800", "1812"), "1776"),
            Pregunta("Historia", "¿Quién descubrió América?", listOf("Cristóbal Colón", "Ferdinand Magellan", "Vasco da Gama", "Juan Sebastián Elcano"), "Cristóbal Colón"),

            // Geografía
            Pregunta("Geografía", "¿Cuál es el país más grande del mundo?", listOf("Rusia", "Canadá", "China", "Estados Unidos"), "Rusia"),
            Pregunta("Geografía", "¿Cuál es la capital de Japón?", listOf("Seúl", "Pekín", "Tokio", "Bangkok"), "Tokio"),
            Pregunta("Geografía", "¿Qué río es el más largo del mundo?", listOf("Amazonas", "Nilo", "Yangtsé", "Mississippi"), "Amazonas"),
            Pregunta("Geografía", "¿En qué continente se encuentra el Desierto del Sahara?", listOf("Asia", "África", "América", "Oceanía"), "África"),
            Pregunta("Geografía", "¿Cuál es la capital de Australia?", listOf("Sídney", "Melbourne", "Canberra", "Perth"), "Canberra"),

            // Deportes
            Pregunta("Deportes", "¿Cuántos jugadores tiene un equipo de fútbol?", listOf("9", "10", "11", "12"), "11"),
            Pregunta("Deportes", "¿Quién ganó el Mundial de Fútbol 2018?", listOf("Brasil", "Francia", "Argentina", "Alemania"), "Francia"),
            Pregunta("Deportes", "¿Cuántos puntos se obtiene por un gol en fútbol?", listOf("1", "2", "3", "4"), "1"),
            Pregunta("Deportes", "¿En qué deporte se utiliza un aro para encestar?", listOf("Fútbol", "Baloncesto", "Voleibol", "Rugby"), "Baloncesto"),
            Pregunta("Deportes", "¿Qué deporte es conocido como 'fútbol americano'?", listOf("Rugby", "Fútbol", "Béisbol", "Fútbol americano"), "Fútbol americano")
        )
    }
}

// Clase Juego
class Juego {
    private val preguntas = PreguntaRepository.obtenerPreguntas()
    private var preguntasPorCategoria = listOf<Pregunta>()
    private var puntaje = 0
    private var vidas = 3
    private var indicePregunta = 0

    // Obtener una categoría aleatoria de las preguntas
    fun obtenerCategoriaAleatoria(): String {
        val categorias = preguntas.map { it.categoria }.distinct()
        return categorias.random()
    }

    // Configurar las preguntas por la categoría seleccionada
    fun configurarPreguntasPorCategoria(categoria: String) {
        preguntasPorCategoria = preguntas.filter { it.categoria == categoria }
        indicePregunta = 0
    }

    // Obtener la pregunta actual
    fun obtenerPreguntaActual(): Pregunta? {
        return if (indicePregunta < preguntasPorCategoria.size) preguntasPorCategoria[indicePregunta] else null
    }

    // Verificar la respuesta seleccionada
    fun verificarRespuesta(opcion: String): Boolean {
        val preguntaActual = obtenerPreguntaActual() ?: return false
        val esCorrecta = opcion == preguntaActual.respuestaCorrecta

        if (esCorrecta) {
            puntaje += 10
        } else {
            vidas--
        }
        indicePregunta++
        return esCorrecta
    }

    // Obtener el puntaje actual
    fun obtenerPuntaje(): Int = puntaje

    // Obtener las vidas restantes
    fun obtenerVidas(): Int = vidas

    // Verificar si el juego ha terminado
    fun juegoTerminado(): Boolean = vidas <= 0 || indicePregunta >= preguntasPorCategoria.size

    // Método para reiniciar el juego
    fun reiniciarJuego() {
        puntaje = 0
        vidas = 3
        indicePregunta = 0
    }
}
