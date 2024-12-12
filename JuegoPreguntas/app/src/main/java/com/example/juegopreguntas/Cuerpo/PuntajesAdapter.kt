// Creador: Alvaro Andre Machaca Melendez
// Fecha de creación: 11 de diciembre de 2024
// Descripción: Adaptador para mostrar una lista de puntajes.

package com.example.juegopreguntas.Cuerpo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.juegopreguntas.R

class PuntajesAdapter(private val puntajes: List<Pair<String, Int>>) :
    RecyclerView.Adapter<PuntajesAdapter.PuntajeViewHolder>() {

    class PuntajeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Referencias a los TextViews.
        val tvNombreUsuario: TextView = itemView.findViewById(R.id.tvNombreUsuario)
        val tvPuntaje: TextView = itemView.findViewById(R.id.tvPuntaje)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PuntajeViewHolder {
        // Inflar el layout del item.
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_puntaje, parent, false)
        return PuntajeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PuntajeViewHolder, position: Int) {
        // Mostrar el nombre de usuario y el puntaje.
        val puntaje = puntajes[position]
        holder.tvNombreUsuario.text = puntaje.first
        holder.tvPuntaje.text = puntaje.second.toString()
    }

    override fun getItemCount(): Int = puntajes.size // Devuelve el número de puntajes.
}
