// Creado por: Andrea del Rosario Velazco Yana
// Fecha de creación: 11 de diciembre de 2024
// Descripción: ViewModel que almacena el índice de la pregunta actual y gestiona el temporizador.

package com.example.juegopreguntas.Cuerpo

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.fragment.app.activityViewModels

class  GameViewModel : ViewModel() {

    private val _indicePreguntaActual = MutableLiveData(0)
    val indicePreguntaActual: LiveData<Int> = _indicePreguntaActual

    private val _tiempoRestante = MutableLiveData(30) // 30 segundos por pregunta
    val tiempoRestante: LiveData<Int> = _tiempoRestante

    private var temporizador: CountDownTimer? = null

    // Inicia un temporizador de 30 segundos para cada pregunta
    fun iniciarTemporizador() {
        temporizador?.cancel() // Cancela cualquier temporizador previo
        temporizador = object : CountDownTimer(30000, 1000) { // 30 segundos
            override fun onTick(millisUntilFinished: Long) {
                _tiempoRestante.value = (millisUntilFinished / 1000).toInt()
            }

            override fun onFinish() {
                _tiempoRestante.value = 0
                avanzarPregunta()}

        }.start()
    }

    // Reinicia el índice de la pregunta actual al inicio del juego
    fun reiniciarIndicePregunta() {
        _indicePreguntaActual.value = 0
    }

    // Avanza al índice de la siguiente pregunta y reinicia el temporizador
    fun avanzarPregunta() {
        _indicePreguntaActual.value = _indicePreguntaActual.value?.plus(1)
        iniciarTemporizador() // Reinicia el temporizador para la nueva pregunta
    }

    // Detiene el temporizador en curso (importante para evitar fugas de memoria)
    fun detenerTemporizador() {
        temporizador?.cancel()
        temporizador = null
        Log.d("GameViewModel", "Temporizador detenido.")
    }

    // Detiene el temporizador si el ViewModel es destruido
    override fun onCleared() {
        super.onCleared()
        detenerTemporizador()
    }
}
