// Creador: Enyelbert Anderson Panta Huaracha
// Fecha de creación: 11 de diciembre de 2024
// Descripción: Fragmento que muestra el menú principal del juego.

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

        // Obtiene la referencia al botón "Iniciar Juego".
        val iniciarJuegoButton: Button = view.findViewById(R.id.iniciarJuegoButton)
        // Configura el listener del botón para navegar al fragmento "SeleccionPreguntaFragment".
        iniciarJuegoButton.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_seleccionPreguntaFragment)
        }

        // Obtiene la referencia al botón "Puntajes".
        val puntajesButton: Button = view.findViewById(R.id.puntajesButton)
        // Configura el listener del botón para navegar al fragmento "PuntajesFragment".
        puntajesButton.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_puntajesFragment)
        }

        return view
    }
}