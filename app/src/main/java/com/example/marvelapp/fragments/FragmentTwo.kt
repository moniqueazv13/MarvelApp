package com.example.marvelapp.fragments

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.heroes.adapter.HeroesAdapter
import com.example.marvelapp.model.Result
import com.example.marvelapp.viewmodel.ViewModelCharacters


class FragmentTwo : Fragment(R.layout.fragment_two) {

    var results = mutableListOf<Result>()
    private val recycler by lazy { view?.findViewById<RecyclerView>(R.id.recycler_view) }
    private val viewModelCharacter by viewModels<ViewModelCharacters>()
    lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterCharacter = HeroesAdapter(results)
        recycler?.adapter = adapterCharacter

        viewModelCharacter.listMutableChar.observe(viewLifecycleOwner) {
            it?.let { itChar -> results.addAll(itChar) }
            adapterCharacter.notifyDataSetChanged()
        }

        viewModelCharacter.loading.observe(viewLifecycleOwner) {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }

        viewModelCharacter.errorMessage.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(activity,"Text!",Toast.LENGTH_SHORT).show()            }
        }
    }

}
