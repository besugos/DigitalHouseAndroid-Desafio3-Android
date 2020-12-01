package com.besugos.desafio3dha.home.view

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.besugos.desafio3dha.R
import com.besugos.desafio3dha.home.model.ComicModel
import com.squareup.picasso.Picasso

class ComicViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val imagem = view.findViewById<ImageView>(R.id.imgThumbCover)
    private val idPersonagem = view.findViewById<TextView>(R.id.txtIssueNumber)

    @SuppressLint("SetTextI18n")
    fun bind(personagemModel: ComicModel) {
        idPersonagem.text = " # ${personagemModel.id}"
        val imagePath = personagemModel.thumbnail?.getImagePath()
        Picasso.get().load(imagePath).into(imagem)
    }
}