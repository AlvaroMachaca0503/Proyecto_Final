package com.example.juegopreguntas.Cuerpo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.juegopreguntas.R

class PuntajesFragment : Fragment() {

    private lateinit var puntajeTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_puntajes, container, false)

        puntajeTextView = view.findViewById(R.id.puntajeTextView)

        // Obtener el puntaje del argumento pasado desde el JuegoFragment
        val puntaje = arguments?.getInt("puntaje", 0) ?: 0
        puntajeTextView.text = "Puntaje final: $puntaje"

        return view
    }
}