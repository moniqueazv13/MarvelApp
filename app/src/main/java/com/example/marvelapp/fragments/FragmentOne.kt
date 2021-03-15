package com.example.fragment.recycler.model.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.recyclerview.Heroes
import com.example.marvelapp.recyclerview.HeroesAdapter


class FragmentOne : Fragment() {
    lateinit var recycler : RecyclerView

    private var heroesList = mutableListOf<Heroes>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_one, container, false)

        initView(view)

        val heroes = getHeroes()

//        recycler.layoutManager = LinearLayoutManager(context)
        recycler.layoutManager = GridLayoutManager(context, 3)
        val adapter = HeroesAdapter(heroes)
        recycler.adapter = adapter

        return view

    }

    private fun initView(view: View) {
        recycler = view.findViewById<RecyclerView>(R.id.recycler_view)
    }



    private fun getHeroes(): MutableList<Heroes> {

        heroesList.add(Heroes("Spider-Man",
                R.drawable.spider))
        heroesList.add(Heroes("Spider-Man",
                R.drawable.spider))
        heroesList.add(Heroes("Spider-Man",
                R.drawable.spider))
        return heroesList

    }
}