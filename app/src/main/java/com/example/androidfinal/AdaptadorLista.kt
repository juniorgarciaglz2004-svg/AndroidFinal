package com.example.androidfinal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfinal.bd.DbRepositorioCoches


class AdaptadorLista(val contexto: Context,
                     val inflador: LayoutInflater = contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
    : RecyclerView.Adapter<AdaptadorLista.CreadorItems>() {

 //   lateinit var controlador: AppController
    val bd = DbRepositorioCoches(contexto)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreadorItems {
    //    controlador = AppController(contexto)
        val v = inflador.inflate(R.layout.layout_elemento_lista,
            parent, false)
        return CreadorItems(v)
    }

    override fun onBindViewHolder(
        holder: CreadorItems,
        position: Int
    ) {
        val coche = bd.elemento(position)
        holder.modelo.text = coche.modelo
        holder.marca.text = coche.marca
        holder.anno.text = coche.anno.toString()
        holder.caracteristicas.text = coche.caracteristicas


    }

    override fun getItemCount(): Int {
        return bd.tamanno()
    }

    class CreadorItems(
        val view: View,
        //val controlador: AppController,
        val modelo: TextView = view.findViewById(R.id.recModelo),
        val marca: TextView = view.findViewById(R.id.recMarca),
        val anno: TextView = view.findViewById(R.id.recAnno),
        val caracteristicas: TextView = view.findViewById(R.id.recCaracteristicas)
    ) : RecyclerView.ViewHolder(view) {
        init {
            val layout = itemView.findViewById<LinearLayout>(R.id.layout_elemento)
            layout.setOnClickListener {
                val posicion = bindingAdapterPosition
//                Toast.makeText(context, "Hola $posicion", Toast.LENGTH_LONG)
//                    .show()
                //controlador.vistaPersonaje(posicion)
            }
        }
    }
}










