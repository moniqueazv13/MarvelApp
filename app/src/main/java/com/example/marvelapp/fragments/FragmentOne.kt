package com.example.fragment.recycler.model.fragments

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


class FragmentOne : Fragment() {
    lateinit var recycler : RecyclerView

    private var comicsList = mutableListOf<Comics>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_one, container, false)

        initView(view)

        val comics = getComics()

//        recycler.layoutManager = LinearLayoutManager(context)
        recycler.layoutManager = GridLayoutManager(context, 3)
        val adapter = ComicsAdapter(comics)
        recycler.adapter = adapter

        return view

    }

    private fun initView(view: View) {
        recycler = view.findViewById<RecyclerView>(R.id.recycler_view)
    }



    private fun getComics(): MutableList<Comics> {

        comicsList.add(Comics("Spider-Man",
                R.drawable.spider))
        comicsList.add(Comics("Spider-Man",
                R.drawable.spider))
        comicsList.add(Comics("Spider-Man",
                R.drawable.spider))
        comicsList.add(Comics("Spider-Man",
                R.drawable.spider))
        comicsList.add(Comics("Spider-Man",
                R.drawable.spider))
        return comicsList

    }
}