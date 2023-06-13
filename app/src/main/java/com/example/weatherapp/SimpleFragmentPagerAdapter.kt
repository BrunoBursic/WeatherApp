package com.example.weatherapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SimpleFragmentPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MainFragment()
            }
            else -> {
                SearchFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }
}