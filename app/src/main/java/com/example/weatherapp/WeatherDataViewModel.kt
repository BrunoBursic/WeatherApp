package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.network.WeatherDataApi
import kotlinx.coroutines.launch

class WeatherDataViewModel(coordinatesViewModel: CoordinatesViewModel) : ViewModel(){
    //https://medium.com/@summitkumar/sharing-data-between-viewmodels-in-androids-mvvm-architecture-56a5009e4e63
    //link za share-anje podataka između viewmodela
    private val latLiveData: LiveData<Double> = coordinatesViewModel.getLat()
    private val lonLiveData: LiveData<Double> = coordinatesViewModel.getLon()
    val cityNameLiveData: LiveData<String> = coordinatesViewModel.getcityName()

    fun weatherData() {
        viewModelScope.launch {
            try {
                val weatherData = WeatherDataApi.retrofitService.getWeatherData(
                    latLiveData.value,
                    lonLiveData.value,
                    "metric",
                    "1dfb4f777e8166a625871672bfe34c06"
                )

                val currentData = weatherData.current
                val hourlyData = weatherData.hourly
                val dailyData = weatherData.daily
            } catch (_: Exception) {

            }
        }
    }
}