package com.example.marvelapp.fragments.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.marvelapp.fragments.FragmentOne
import com.example.marvelapp.fragments.FragmentTwo

class MyPagerAdapter(fm :FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> FragmentOne()
            else -> {
                return FragmentTwo()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "COMICS"
            else -> {
                return "HEROES"
            }
        }
    }
}