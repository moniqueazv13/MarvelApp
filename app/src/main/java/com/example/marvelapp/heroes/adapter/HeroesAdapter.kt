package com.example.marvelapp.heroes.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.ui.view.DetailsHeroesActivity
import com.example.marvelapp.heroes.adapter.viewholder.HeroesViewHolder
import com.example.marvelapp.model.Result
import com.squareup.picasso.Picasso

class HeroesAdapter(val heroesList: List<Result>) : RecyclerView.Adapter<HeroesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.hero_item,
            parent,
            false
        )
        return HeroesViewHolder(view)

    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val heroes = heroesList.elementAt(position)

        holder.heroName.text = heroes.name

        Picasso.get().load(heroes.thumbnail?.path + ".jpg").into(holder.heroImage)

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailsHeroesActivity::class.java)
            intent.putExtra("NAME", heroes.name)
//            intent.putExtra("IMAGE", heroes.image)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = heroesList.size
}