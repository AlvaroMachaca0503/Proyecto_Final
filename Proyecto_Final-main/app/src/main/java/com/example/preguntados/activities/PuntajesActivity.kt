package com.example.preguntados.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.preguntados.R
import kotlinx.android.synthetic.main.activity_puntajes.*


class PuntajesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntajes)
        btnBack.setOnClickListener {
            finish()
    }
}