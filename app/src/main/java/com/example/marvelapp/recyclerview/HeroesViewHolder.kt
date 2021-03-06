package com.example.marvelapp.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R

class HeroesViewHolder(view:View) : RecyclerView.ViewHolder(view){
    val nameHero by lazy { view.findViewById<TextView>(R.id.tv_name) }
    val imageHero by lazy { view.findViewById<ImageView>(R.id.iv_image) }
}