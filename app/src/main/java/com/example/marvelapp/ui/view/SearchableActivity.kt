package com.example.marvelapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.marvelapp.R
import com.example.marvelapp.adapter.adapter.heroes.HeroesAdapter
import com.example.marvelapp.model.characters.Result
//import com.example.marvelapp.ui.extensions.hideKeyboard
import com.example.marvelapp.ui.viewmodel.ViewModelCharacters
import kotlinx.android.synthetic.main.activity_searchable.*

class SearchableActivity : AppCompatActivity() {
    private var results = mutableListOf<Result>()
    private val viewModelCharacter by viewModels<ViewModelCharacters>()
    private val characterAdapter by lazy { HeroesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchable)
        recycler_search.adapter = characterAdapter
        observeEvents()
        search.setOnClickListener {
            viewModelCharacter.searchHeroes(search_editText.text)
        }
    }

    private fun observeEvents() {
        viewModelCharacter.listMutableChar.observe(this, Observer {
            it?.let { itChar -> results.addAll(itChar) }
            Log.d("okhttp", "tamanho = ${results.size}")
            characterAdapter.heroesList = it
//            hideKeyboard()
        })
        viewModelCharacter.emptyField.observe(this, Observer {
            Toast.makeText(this, "errado", Toast.LENGTH_LONG).show()
        })

        viewModelCharacter.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
//            hideKeyboard()
        })
    }
}