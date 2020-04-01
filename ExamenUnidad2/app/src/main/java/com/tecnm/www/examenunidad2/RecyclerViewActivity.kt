package com.tecnm.www.examenunidad2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity(), EliminarAnimeDialogFragment.EliminarAnimeDialogListener {

    val onLongItemClickListener: (Int) -> Unit = { position ->
        DialogEliminarAnime(position)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        loadData()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AnimeAdapter(onLongItemClickListener)
    }

    private fun loadData() {
        for (x in 1..20) {
            var estado = "Finalizado"
            if (x % 2 == 0) {
                estado = "Finalizado"
            } else {
                estado = "En producción"
            }
            Singleton.dataSet.add(Anime(
                "Anime ${x.toString()}",
                "Shonnen",
                estado
            ))
        }
    }

    private fun DialogEliminarAnime(position: Int) {
        val dialog = EliminarAnimeDialogFragment(position)
        dialog.show(supportFragmentManager, "EliminarAnimeDialogFragment")
    }

    override fun onDialogPositiveClick(position: Int) {
        val anime = Singleton.dataSet.get(position)
        Singleton.dataSet.removeAt(position)
        recyclerView.adapter?.notifyItemRemoved(position)

        Snackbar.make(recyclerView, "Anime eliminado: ${anime.nombre}", Snackbar.LENGTH_LONG)
            .setAction("Deshacer", {
                Singleton.dataSet.add(position, anime)
                recyclerView.adapter?.notifyItemInserted(position)
            }).show()
    }

    override fun onDialogNegativeClick(position: Int) {
        Toast.makeText(this, "No se elimino el anime", Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.opciones -> {
                startActivity(Intent(this, OpcionesActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
