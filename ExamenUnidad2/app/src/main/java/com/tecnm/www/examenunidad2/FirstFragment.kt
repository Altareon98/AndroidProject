package com.tecnm.www.examenunidad2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nombre = (activity as DatosAnimeActivity).nombre
        val genero = (activity as DatosAnimeActivity).genero
        val estado = (activity as DatosAnimeActivity).estado
        val imagen : Int = (activity as DatosAnimeActivity).imagen!!

        tvNombreAnime.text = nombre
        tvGeneroAnime.text = genero
        tvEstadoAnime.text = estado
        ivImagenAnime.setImageResource(imagen)

        rtbRatingAnime.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Toast.makeText(view.context,
                "Puntuaste con ${rating} estrellas",
                Toast.LENGTH_LONG).show()
        }

        button.setOnClickListener{
            mandarComentario()
        }
    }

    fun mandarComentario() {
        if (!etComentario.text.isEmpty()) {
            val comentario = etComentario.text
            etComentario.text.clear()
            Toast.makeText(activity, "Su comentario ha sido enviado", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(activity, "Debe escribir un comentario primero!", Toast.LENGTH_LONG).show()
        }
    }
}
