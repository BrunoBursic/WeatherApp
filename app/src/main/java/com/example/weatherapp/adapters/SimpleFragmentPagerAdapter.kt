package com.example.weatherapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.weatherapp.fragments.MainFragment
import com.example.weatherapp.fragments.SearchFragment

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