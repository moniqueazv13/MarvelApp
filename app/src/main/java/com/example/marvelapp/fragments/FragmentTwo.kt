package com.example.marvelapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.heroes.adapter.HeroesAdapter
import com.example.marvelapp.heroes.model.Heroes

class FragmentTwo : Fragment(R.layout.fragment_two) {
    lateinit var recycler: RecyclerView

    private var heroesList = mutableListOf<Heroes>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getHeroes()
        recycler = view.findViewById<RecyclerView>(R.id.recycler_view_heroes)
        val adapter = HeroesAdapter(heroesList)
        recycler.adapter = adapter

    }

    private fun getHeroes() {

        heroesList.add(Heroes("BLACK WIDOW",
                R.drawable.hero_image))
        heroesList.add(Heroes("BLACK WIDOW",
                R.drawable.hero_image))
        heroesList.add(Heroes("BLACK WIDOW",
                R.drawable.hero_image))
        heroesList.add(Heroes("BLACK WIDOW",
                R.drawable.hero_image))

    }
}