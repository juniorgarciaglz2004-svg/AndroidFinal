package com.example.androidfinal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfinal.bd.DbRepositorioCoches
import com.example.androidfinal.modelos.Coche


class AdaptadorLista(val contexto: Context,
                     private val onLinkClick: (Coche) -> Unit,
                     val inflador: LayoutInflater = contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
    : RecyclerView.Adapter<AdaptadorLista.CreadorItems>() {

    val listado = DbRepositorioCoches(contexto).listado()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreadorItems {
        val v = inflador.inflate(R.layout.layout_elemento_lista,
            parent, false)
        return CreadorItems(v)
    }

    override fun onBindViewHolder(
        holder: CreadorItems,
        position: Int
    ) {
        val coche = listado[position]
        val iconoBusqueda = "\uD83D\uDD0D"
        holder.modelo.text = "${coche.modelo} (${coche.id}) ${iconoBusqueda}"
        holder.modelo.setOnClickListener {
            onLinkClick(coche)
        }
        holder.marca.text = coche.marca
        holder.anno.text = coche.anno.toString()
        holder.caracteristicas.text = coche.caracteristicas
    }

    override fun getItemCount(): Int {
        return listado.size
    }

    class CreadorItems(
        val view: View,
        val modelo: TextView = view.findViewById(R.id.recModelo),
        val marca: TextView = view.findViewById(R.id.recMarca),
        val anno: TextView = view.findViewById(R.id.recAnno),
        val caracteristicas: TextView = view.findViewById(R.id.recCaracteristicas)
    ) : RecyclerView.ViewHolder(view)
}










