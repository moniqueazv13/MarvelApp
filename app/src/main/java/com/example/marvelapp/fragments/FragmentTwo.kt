package com.example.fragment.recycler.model.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.comics.adapter.ComicsAdapter
import com.example.marvelapp.comics.model.Comics
import com.example.marvelapp.heroes.adapter.HeroesAdapter
import com.example.marvelapp.heroes.model.Heroes

class FragmentTwo : Fragment() {
    lateinit var recycler : RecyclerView

    private var heroesList = mutableListOf<Heroes>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_two, container, false)

        initView(view)

        val heroes = getHeroes()

//        recycler.layoutManager = LinearLayoutManager(context)
        recycler.layoutManager = GridLayoutManager(context, 2)
        val adapter = HeroesAdapter(heroes)
        recycler.adapter = adapter

        return view

    }

    private fun initView(view: View) {
        recycler = view.findViewById<RecyclerView>(R.id.recycler_view_heroes)
    }



    private fun getHeroes(): MutableList<Heroes> {

        heroesList.add(Heroes("Spider-Man",
                R.drawable.hero_image))
        heroesList.add(Heroes("Spider-Man",
                R.drawable.hero_image))
        heroesList.add(Heroes("Spider-Man",
                R.drawable.hero_image))
        heroesList.add(Heroes("Spider-Man",
                R.drawable.hero_image))
        heroesList.add(Heroes("Spider-Man",
                R.drawable.hero_image))
        return heroesList

    }
}