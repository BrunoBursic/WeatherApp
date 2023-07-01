package com.example.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSearchBinding
import com.example.weatherapp.viewmodels.CoordinatesViewModel
import com.example.weatherapp.viewmodels.MyViewModelFactory
import com.example.weatherapp.viewmodels.WeatherDataViewModel


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    private val viewModel: CoordinatesViewModel by activityViewModels()
    private val viewModel2: WeatherDataViewModel by activityViewModels { MyViewModelFactory(viewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.coordinates("Zagreb")
        viewModel2.weatherData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.coordinatesViewModel = viewModel
        binding.searchFragment = this


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener { enterCity() }
    }

    private fun enterCity() {
        //isprobat ako se ne unese ništa a klikne se gumb
        val city = binding.nameOfCity.text.toString()

        viewModel.coordinates(city)
        viewModel2.weatherData()
    }
}