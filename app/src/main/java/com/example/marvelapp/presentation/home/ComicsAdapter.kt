package com.example.marvelapp.presentation.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.adapter.adapter.comics.viewholder.ComicsViewHolder
import com.example.marvelapp.model.ui.ComicsUi
import com.example.marvelapp.presentation.home.details.DetailsComicsActivity
import com.squareup.picasso.Picasso


class ComicsAdapter() : RecyclerView.Adapter<ComicsViewHolder>() {

    var comicsList = listOf<ComicsUi>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.comic_item,
            parent,
            false
        )
        return ComicsViewHolder(view)
    }

//    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
//        val name = holder.comicName
//        name.text = comicsList[position].title
//
//        val image = holder.comicImage
//        image.setImageResource(comicsList[position].image)
//
//        holder.itemView.setOnClickListener {
//            val intent = Intent(it.context, DetailsComicsActivity::class.java)
//            intent.putExtra("TITLE", comicsList[position].title)
//            intent.putExtra("IMAGE", comicsList[position].image)
//
//            it.context.startActivity(intent)
//        }
//    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val comics = comicsList[position]
        holder.comicName.text = comics.title
//        holder.comicName.text  = "# " + comics.id.toString()
        comics.image?.let {
            Picasso.get().load(it).into(holder.comicImage)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailsComicsActivity::class.java)
            intent.putExtra("COMICS",comics)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = comicsList.size
}