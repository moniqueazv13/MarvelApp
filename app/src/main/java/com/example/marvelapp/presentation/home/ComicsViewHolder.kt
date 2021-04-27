package com.example.marvelapp.adapter.adapter.comics.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R

class ComicsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val comicName by lazy { view.findViewById<TextView>(R.id.tv_name) }
    val comicImage by lazy { view.findViewById<ImageView>(R.id.iv_image) }
}