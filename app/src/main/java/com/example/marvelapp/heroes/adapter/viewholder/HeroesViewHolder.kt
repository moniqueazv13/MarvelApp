package com.example.marvelapp.heroes.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R

class HeroesViewHolder(view:View) : RecyclerView.ViewHolder(view){
    val heroName by lazy { view.findViewById<TextView>(R.id.tv_hero_name) }
    val heroImage by lazy { view.findViewById<ImageView>(R.id.iv_hero_image) }

}