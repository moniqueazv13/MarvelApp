package com.example.marvelapp.recyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R

class HeroesAdapter(private val heroesList: MutableList<Heroes>): RecyclerView.Adapter<HeroesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hero_item,
                parent,
                false)
        return HeroesViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val name = holder.nameHero
        name.text = heroesList[position].name

        val image = holder.imageHero
        image.setImageResource(heroesList[position].image)

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailsHeroesActivity::class.java)
            intent.putExtra("NAME", heroesList[position].name)
            intent.putExtra("IMAGE", heroesList[position].image)

            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return heroesList.size
    }
}