// Creador: Enyelbert Anderson Panta Huaracha
// Fecha de creación: 11 de diciembre de 2024
// Descripción: Adaptador para mostrar opciones de respuesta en un RecyclerView.

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


     // Crea un nuevo ViewHolder para mostrar una opción de respuesta.

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpcionViewHolder {
        // Inflar el layout simple_list_item_1 para cada opción.
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return OpcionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OpcionViewHolder, position: Int) {
        // Bindear la opción en la posición actual al ViewHolder.
        holder.bind(opciones[position])
    }


      //Devuelve la cantidad de opciones de respuesta en la lista.

    override fun getItemCount(): Int = opciones.size


   //ViewHolder para mostrar una opción de respuesta.

    inner class OpcionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = itemView.findViewById(android.R.id.text1)


       // Enlaza una opción de respuesta al TextView y configura el listener de clic.
        fun bind(opcion: String) {
            textView.text = opcion
            // Llamar a la función onOpcionSeleccionada con la opción seleccionada cuando se hace clic.
            itemView.setOnClickListener {
                onOpcionSeleccionada(opcion)
            }
        }
    }
}