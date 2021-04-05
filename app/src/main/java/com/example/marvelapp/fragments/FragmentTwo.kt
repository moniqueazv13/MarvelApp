package com.example.marvelapp.fragments

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.comics.adapter.ComicsAdapter
import com.example.marvelapp.comics.model.Comics
import com.example.marvelapp.heroes.adapter.HeroesAdapter
import com.example.marvelapp.heroes.model.Heroes
import com.example.marvelapp.model.Result
import com.example.marvelapp.viewmodel.ViewModelCharacter
import kotlinx.android.synthetic.main.fragment_one.*


class FragmentTwo : Fragment(R.layout.fragment_two) {

    private var results = mutableListOf<Result>()

    val viewModelCharacter = ViewModelProviders.of(this).get<>(ViewModelCharacter::class.java)
    }
    lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recycler_view)
        val adapterCharacter = HeroesAdapter(results)
//        , this)
        recycler_view.adapter = adapterCharacter
        recycler.adapter = adapterCharacter


        viewModelCharacter.listMutableChar.observe(this, Observer {
            it?.let { itChar -> results.addAll(itChar) }
            adapterCharacter.notifyDataSetChanged()
        })

        viewModelCharacter.loading.observe(this, Observer {
            if (it) {
                progressBar.visibility = VISIBLE
            } else {
                progressBar.visibility = GONE
            }
        })
        viewModelCharacter.errorMessage.observe(this, Observer {
            it?.let {
                Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
            }
        })
    }
}
