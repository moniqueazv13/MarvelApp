package com.example.marvelapp.fragments

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.heroes.adapter.HeroesAdapter
import com.example.marvelapp.viewmodel.ViewModelCharacters


class FragmentTwo : Fragment(R.layout.fragment_two) {

    private val recycler by lazy { view?.findViewById<RecyclerView>(R.id.recycler_view_heroes) }
    private val viewModelCharacter by viewModels<ViewModelCharacters>()
    private val progressBar by lazy { view?.findViewById<ProgressBar>(R.id.progressBar) }
    private val characterAdapter by lazy { HeroesAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler?.adapter = characterAdapter
        observeEvents()
        viewModelCharacter.getAllCharacters()
    }

//    override fun onResume() {
//        super.onResume()
//        viewModelCharacter.getAllCharacters()
//    }

    private fun observeEvents() {
        viewModelCharacter.listMutableChar.observe(viewLifecycleOwner) { heroes ->
            characterAdapter.heroesList = heroes
        }

        viewModelCharacter.loading.observe(viewLifecycleOwner) {
            if (it) {
                progressBar?.visibility = View.VISIBLE
            } else {
                progressBar?.visibility = View.GONE
            }
        }

        viewModelCharacter.errorMessage.observe(viewLifecycleOwner) {
                Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }
    }

}
