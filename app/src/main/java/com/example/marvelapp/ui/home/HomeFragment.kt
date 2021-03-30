package com.example.marvelapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.marvelapp.R
import com.example.marvelapp.fragments.FragmentOne
import com.example.marvelapp.fragments.FragmentTwo
import com.example.marvelapp.fragments.adapter.MyPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewPagerAdapter = MyPagerAdapter(childFragmentManager)
        viewPagerAdapter.addFragment(FragmentOne(), "Comic")
        viewPagerAdapter.addFragment(FragmentTwo(), "Heroes")
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

    }
}