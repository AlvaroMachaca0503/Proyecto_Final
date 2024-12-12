package com.example.juegopreguntas.Cuerpo

// GameViewModel.kt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _indicePreguntaActual = MutableLiveData<Int>(0)
    val indicePreguntaActual: LiveData<Int> = _indicePreguntaActual


    fun reiniciarIndicePregunta() {
        _indicePreguntaActual.value = 0
    }
}