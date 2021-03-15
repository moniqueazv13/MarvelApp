package com.example.marvelapp.heroes.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.view.DetailsHeroesActivity
import com.example.marvelapp.heroes.adapter.viewholder.HeroesViewHolder
import com.example.marvelapp.heroes.model.Heroes

class HeroesAdapter(val heroesList: MutableList<Heroes>): RecyclerView.Adapter<HeroesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hero_item,
                parent,
                false)
        return HeroesViewHolder(view)

    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val name = holder.heroName
        name.text = heroesList[position].name

        val image = holder.heroImage
        image.setImageResource(heroesList[position].image)

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailsHeroesActivity::class.java)
            intent.putExtra("NAME", heroesList[position].name)
            intent.putExtra("IMAGE", heroesList[position].image)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = heroesList.size
}