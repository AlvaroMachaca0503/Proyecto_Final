package com.example.juegopreguntas

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Reproducir música de fondo
        mediaPlayer = MediaPlayer.create(this, R.raw.musica_fondo)
        mediaPlayer.start()


        window.decorView.setBackgroundResource(R.drawable.fondo_pantalla)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}