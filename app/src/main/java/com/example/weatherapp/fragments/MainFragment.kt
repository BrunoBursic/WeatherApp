package com.example.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.adapters.DailyAdapter
import com.example.weatherapp.adapters.HourlyAdapter
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.viewmodels.WeatherDataViewModel

class MainFragment : Fragment() {
    //https://www.youtube.com/watch?v=UbP8E6I91NA
    //video za implementaciju recyclerview-a

    private lateinit var binding: FragmentMainBinding
    private val viewModel: WeatherDataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.hourlyRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.hourlyRecyclerView.setHasFixedSize(true)
        binding.hourlyRecyclerView.adapter = HourlyAdapter(viewModel.hourlyData)

        binding.dailyRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.dailyRecyclerView.setHasFixedSize(true)
        binding.dailyRecyclerView.adapter = DailyAdapter(viewModel.dailyData)
    }
}