// Creador: Enyelbert Anderson Panta Huaracha
// Fecha de creación: 11 de diciembre de 2024
// Descripción: ViewModel que almacena el índice de la pregunta actual.

package com.example.juegopreguntas.Cuerpo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    // Almacena el índice de la pregunta actual como un MutableLiveData.
    private val _indicePreguntaActual = MutableLiveData<Int>(0)
    // Expone el índice de la pregunta actual como un LiveData inmutable.
    val indicePreguntaActual: LiveData<Int> = _indicePreguntaActual

    // Reinicia el índice de la pregunta actual a 0.
    fun reiniciarIndicePregunta() {
        _indicePreguntaActual.value = 0
    }
}