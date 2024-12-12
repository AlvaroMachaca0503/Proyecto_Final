package com.example.juegopreguntas.Cuerpo // Reemplaza con tu paquete

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.juegopreguntas.R // Reemplaza con tu paquete

class PuntajesAdapter(private val puntajes: List<Pair<String, Int>>) :
    RecyclerView.Adapter<PuntajesAdapter.PuntajeViewHolder>() {

    class PuntajeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombreUsuario: TextView = itemView.findViewById(R.id.tvNombreUsuario)
        val tvPuntaje: TextView = itemView.findViewById(R.id.tvPuntaje)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PuntajeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_puntaje, parent, false)
        return PuntajeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PuntajeViewHolder, position: Int) {
        val puntaje = puntajes[position]
        holder.tvNombreUsuario.text = puntaje.first
        holder.tvPuntaje.text = puntaje.second.toString()
    }

    override fun getItemCount(): Int = puntajes.size
}