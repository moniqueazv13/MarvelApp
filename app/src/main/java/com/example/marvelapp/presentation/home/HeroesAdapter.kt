package com.example.marvelapp.adapter.adapter.heroes

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.adapter.adapter.heroes.viewholder.HeroesViewHolder
import com.example.marvelapp.model.characters.Result
import com.example.marvelapp.presentation.home.details.DetailsHeroesActivity
import com.squareup.picasso.Picasso

class HeroesAdapter() : RecyclerView.Adapter<HeroesViewHolder>() {

    var heroesList = listOf<Result>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.hero_item,
            parent,
            false
        )
        return HeroesViewHolder(view)

    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val heroes = heroesList[position]
        holder.heroName.text = heroes.name
        Picasso.get().load(heroes.thumbnail?.path + ".jpg").into(holder.heroImage)

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailsHeroesActivity::class.java)
            intent.putExtra("HEROES", heroes)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = heroesList.size
}