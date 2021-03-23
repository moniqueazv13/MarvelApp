package com.example.marvelapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.comics.model.Comics
import com.example.marvelapp.comics.adapter.ComicsAdapter


class FragmentOne : Fragment(R.layout.fragment_one) {
    lateinit var recycler: RecyclerView

    private var comicsList = mutableListOf<Comics>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getComics()
        recycler = view.findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = ComicsAdapter(comicsList)
        recycler.adapter = adapter
    }

    private fun getComics() {

        comicsList.clear()
        comicsList.add(Comics("Spider-Man",
                R.drawable.spider))
        comicsList.add(Comics("Spider-Man",
                R.drawable.spider))
        comicsList.add(Comics("Spider-Man",
                R.drawable.spider))

    }
}