package com.example.marvelapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.marvelapp.R
import com.example.marvelapp.heroes.adapter.HeroesAdapter
import com.example.marvelapp.heroes.model.Heroes
import kotlinx.android.synthetic.main.fragment_two.*

class FragmentTwo : Fragment(R.layout.fragment_two) {

    private val heroesList = mutableListOf<Heroes>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getHeroes()
        val adapter = HeroesAdapter(heroesList)
        recycler_view_heroes.adapter = adapter
    }

    private fun getHeroes() {
        heroesList.add(Heroes("BLACK WIDOW", R.drawable.hero_image))
        heroesList.add(Heroes("BLACK WIDOW", R.drawable.hero_image))
        heroesList.add(Heroes("BLACK WIDOW", R.drawable.hero_image))
        heroesList.add(Heroes("BLACK WIDOW", R.drawable.hero_image))
    }
}