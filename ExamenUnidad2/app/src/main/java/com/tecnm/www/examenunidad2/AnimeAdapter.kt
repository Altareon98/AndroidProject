package com.tecnm.www.examenunidad2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.animes_recyclerview_item.view.*

class AnimeAdapter(private val longItemClickListener: (Int) -> Unit) :
        RecyclerView.Adapter<AnimeAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // make reference to views on the activity
        val tvNombre = v.tvNombre
        val tvGenero = v.tvGenero
        val tvEstado = v.tvEstado
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.animes_recyclerview_item, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount() = Singleton.dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = Singleton.dataSet.get(position)
        // handle click on item and replace its TextViews values
        holder.tvNombre.text = dataItem.nombre
        holder.tvGenero.text = dataItem.genero
        holder.tvEstado.text = dataItem.estado

        holder.itemView.setOnClickListener {
            var imagen = 0
            if (position < 1) imagen = position + 1
            if (imagen % 2 == 0) {
                imagen = R.drawable.boku_no_hero_academia
            } else {
                imagen = R.drawable.haikyuu
            }
            // navigate to the item data activity
            val intent = Intent(holder.itemView.context, DatosAnimeActivity::class.java)
            intent.putExtra("nombre", dataItem.nombre)
            intent.putExtra("genero", dataItem.genero)
            intent.putExtra("estado", dataItem.estado)
            intent.putExtra("imagen", imagen)

            holder.itemView.context.startActivity(intent)
        }

        holder.itemView.setOnLongClickListener {
            longItemClickListener.invoke(position)
            true
        }
    }
}