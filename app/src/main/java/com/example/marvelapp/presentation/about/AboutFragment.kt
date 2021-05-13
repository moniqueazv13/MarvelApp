package com.example.marvelapp.presentation.about


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.marvelapp.R
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment(R.layout.fragment_about) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val link = "https://developer.marvel.com/"

        button_marvel_api.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
        }

    }

}



