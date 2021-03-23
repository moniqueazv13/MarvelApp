package com.example.marvelapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.comics.model.Comics
import com.example.marvelapp.comics.adapter.ComicsAdapter


class FragmentOne : Fragment(R.layout.fragment_one) {
    lateinit var recycler : RecyclerView

    private var comicsList = mutableListOf<Comics>()

    private val  adapter  by lazy { ComicsAdapter() }


    override fun onResume() {
        super.onResume()

        recycler.adapter = adapter
        adapter.comicsList = comicsList
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById<RecyclerView>(R.id.recycler_view)
        getComics()

        recycler.adapter = adapter
        adapter.comicsList = comicsList


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