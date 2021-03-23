package com.example.marvelapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.marvelapp.R
import com.example.marvelapp.comics.adapter.ComicsAdapter
import com.example.marvelapp.comics.model.Comics
import kotlinx.android.synthetic.main.fragment_one.*


class FragmentOne : Fragment(R.layout.fragment_one) {

    private val comicsList = mutableListOf<Comics>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getComics()
        val adapter = ComicsAdapter(comicsList)
        recycler_view.adapter = adapter
    }

    private fun getComics() {
        comicsList.clear()
        comicsList.add(Comics("Spider-Man", R.drawable.spider))
        comicsList.add(Comics("Spider-Man", R.drawable.spider))
        comicsList.add(Comics("Spider-Man", R.drawable.spider))
    }
}