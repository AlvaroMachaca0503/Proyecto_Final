//Creado por: Andrea del Rosario Velazco Yana
// Fecha de creación: 11 de diciembre de 2024
// Descripción: ViewModel que almacena el índice de la pregunta actual.

package com.example.juegopreguntas.Cuerpo

// GameViewModel.kt
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _indicePreguntaActual = MutableLiveData(0)
    val indicePreguntaActual: LiveData<Int> = _indicePreguntaActual

    private val _tiempoRestante = MutableLiveData(30) // 30 segundos por pregunta
    val tiempoRestante: LiveData<Int> = _tiempoRestante

    private var temporizador: CountDownTimer? = null

    fun iniciarTemporizador() {
        temporizador?.cancel() // Cancela cualquier temporizador previo
        temporizador = object : CountDownTimer(30000, 1000) { // 30 segundos
            override fun onTick(millisUntilFinished: Long) {
                _tiempoRestante.value = (millisUntilFinished / 1000).toInt()
            }

            override fun onFinish() {
                _tiempoRestante.value = 0
            }
        }.start()
    }

    fun reiniciarIndicePregunta() {
        _indicePreguntaActual.value = 0
    }

    fun avanzarPregunta() {
        _indicePreguntaActual.value = _indicePreguntaActual.value?.plus(1)
        iniciarTemporizador() // Reinicia el temporizador para la nueva pregunta
    }

    fun detenerTemporizador() {
        temporizador?.cancel()
    }
}
