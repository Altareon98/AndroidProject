package com.tecnm.www.examenunidad2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_datos_anime.*
import kotlinx.android.synthetic.main.animes_recyclerview_item.*
import kotlinx.android.synthetic.main.fragment_first.*

class DatosAnimeActivity : AppCompatActivity() {
    var nombre : String? = null
    var genero : String? = null
    var estado : String? = null
    var imagen : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_anime)
        setSupportActionBar(toolbar)

        nombre = intent.extras?.getString("nombre", "")
        genero = intent.extras?.getString("genero", "")
        estado = intent.extras?.getString("estado", "")
        imagen = intent.extras?.getInt("imagen", 0)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "¿Compartir anime?", Snackbar.LENGTH_LONG)
                .setAction("Compartir", {
                    compartirAnime()
                }).show()
        }
    }

    private fun mostrarToast() {
        Toast.makeText(this, "Se invocó a miMetodo", Toast.LENGTH_LONG).show()
    }

    private fun compartirAnime() {
        // Create the text message with a string
        val mensaje = "Te recomiendo este anime ${nombre}. Es del genero ${genero} y está ${estado}"
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, mensaje)
            type = "text/plain"
        }

        // Always use string resources for UI text.
        // This says something like "Share this photo with"
        val title: String = resources.getString(R.string.chooser_title)
        // Create intent to show the chooser dialog
        val chooser: Intent = Intent.createChooser(sendIntent, title)

        // Verify the original intent will resolve to at least one activity
        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(chooser)
        }
    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.opciones -> {
                val intent = Intent(this, OpcionesActivity::class.java)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }*/
}
