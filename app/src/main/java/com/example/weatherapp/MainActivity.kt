package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController*/

        //setupActionBarWithNavController(navController)

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main)
        // Find the view pager that will allow the user to swipe between fragments
        val viewPager = findViewById<ViewPager>(R.id.viewpager)
        // Create an adapter that knows which fragment should be shown on each page
        val adapter = SimpleFragmentPagerAdapter(supportFragmentManager)
        // Set the adapter onto the view pager
        viewPager.adapter = adapter
    }
}