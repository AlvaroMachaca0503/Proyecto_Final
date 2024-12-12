package com.example.andrea.Cuerpo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OpcionesAdapter(
    private val opciones: List<String>,
    private val onOpcionSeleccionada: (String) -> Unit
) : RecyclerView.Adapter<OpcionesAdapter.OpcionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpcionViewHolder {
        // Inflar el layout de cada ítem de la lista (opción de respuesta)
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return OpcionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OpcionViewHolder, position: Int) {
        // Enlazar el dato de la opción a la vista
        holder.bind(opciones[position])
    }

    override fun getItemCount(): Int = opciones.size

    inner class OpcionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = itemView.findViewById(android.R.id.text1)

        // Función para bindear cada opción al TextView
        fun bind(opcion: String) {
            textView.text = opcion
            // Configurar la acción cuando se hace clic en una opción
            itemView.setOnClickListener {
                onOpcionSeleccionada(opcion)
            }
        }
    }
}