package com.example.juegopreguntas.Cuerpo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.juegopreguntas.R

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        val iniciarJuegoButton: Button = view.findViewById(R.id.iniciarJuegoButton)
        val puntajesButton: Button = view.findViewById(R.id.puntajesButton)

        iniciarJuegoButton.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_juegoFragment)
        }

        puntajesButton.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_puntajesFragment)
        }

        return view
    }
}