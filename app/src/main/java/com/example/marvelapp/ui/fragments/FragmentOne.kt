package com.example.marvelapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.adapter.adapter.comics.ComicsAdapter
import com.example.marvelapp.adapter.adapter.heroes.HeroesAdapter
import com.example.marvelapp.ui.viewmodel.ViewModelComics


class FragmentOne : Fragment(R.layout.fragment_one) {

    private val recycler by lazy { view?.findViewById<RecyclerView>(R.id.recycler_view_comics) }
    private val viewModelComics by viewModels<ViewModelComics>()
    private val progressBar by lazy { view?.findViewById<ProgressBar>(R.id.progressBar) }
    private val comicsAdapter by lazy { ComicsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler?.adapter = comicsAdapter
        observeEvents()
        viewModelComics.getAllComics()
    }

    private fun observeEvents() {
        viewModelComics.listMutableComics.observe(viewLifecycleOwner) { comics ->
            comicsAdapter.comicsList = comics
        }

        viewModelComics.loading.observe(viewLifecycleOwner) {
            if (it) {
                progressBar?.visibility = View.VISIBLE
            } else {
                progressBar?.visibility = View.GONE
            }
        }

        viewModelComics.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }
    }






//    private val comicsList = mutableListOf<Comics>()
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        getComics()
//        val adapter = ComicsAdapter(comicsList)
//        recycler_view.adapter = adapter
//    }
//
//    private fun getComics() {
//        comicsList.clear()
//        comicsList.add(Comics("Spider-Man", R.drawable.spider))
//        comicsList.add(Comics("Spider-Man", R.drawable.spider))
//        comicsList.add(Comics("Spider-Man", R.drawable.spider))
//    }

}