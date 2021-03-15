package com.example.marvelapp.comics.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.comics.model.Comics
import com.example.marvelapp.comics.adapter.viewholder.ComicsViewHolder
import com.example.marvelapp.comics.DetailsComicsActivity

class ComicsAdapter(private val comicsList: MutableList<Comics>): RecyclerView.Adapter<ComicsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comic_item,
                parent,
                false)
        return ComicsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val name = holder.comicName
        name.text = comicsList[position].name

        val image = holder.comicImage
        image.setImageResource(comicsList[position].image)

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailsComicsActivity::class.java)
            intent.putExtra("NAME", comicsList[position].name)
            intent.putExtra("IMAGE", comicsList[position].image)

            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return comicsList.size
    }
}