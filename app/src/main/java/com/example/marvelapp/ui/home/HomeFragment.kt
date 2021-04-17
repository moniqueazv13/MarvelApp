package com.example.marvelapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.marvelapp.R
import com.example.marvelapp.ui.view.SearchableActivity
import com.example.marvelapp.ui.fragments.FragmentOne
import com.example.marvelapp.ui.fragments.FragmentTwo
import com.example.marvelapp.ui.fragments.adapter.MyPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewPagerAdapter = MyPagerAdapter(childFragmentManager)
        viewPagerAdapter.addFragment(FragmentOne(), "Comic")
        viewPagerAdapter.addFragment(FragmentTwo(), "Heroes")
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        search_click.setOnClickListener {
            val intent = Intent(activity, SearchableActivity::class.java)
            startActivity(intent)
        }
    }
}

