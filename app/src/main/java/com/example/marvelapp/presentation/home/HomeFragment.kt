package com.example.marvelapp.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.marvelapp.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewPagerAdapter = MyPagerAdapter(childFragmentManager)
        viewPagerAdapter.addFragment(FragmentComics(), "Comics")
        viewPagerAdapter.addFragment(FragmentHeroes(), "Heroes")
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        search_click.setOnClickListener {
            val intent = Intent(activity, SearchableActivity::class.java)
            startActivity(intent)
        }
    }
}

