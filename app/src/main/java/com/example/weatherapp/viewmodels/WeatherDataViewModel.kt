package com.example.weatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.network.WeatherDataApi
import com.example.weatherapp.network.WeatherDataResponse
import kotlinx.coroutines.launch

class WeatherDataViewModel (private val coordinatesViewModel: CoordinatesViewModel) : ViewModel(){
    //https://medium.com/@summitkumar/sharing-data-between-viewmodels-in-androids-mvvm-architecture-56a5009e4e63
    //link za share-anje podataka između viewmodela

    private val latLiveData: LiveData<Double> = coordinatesViewModel.getLat()
    private val lonLiveData: LiveData<Double> = coordinatesViewModel.getLon()
    val cityNameLiveData: LiveData<String> = coordinatesViewModel.getcityName()

    lateinit var currentData: WeatherDataResponse.CurrentWeather
    lateinit var hourlyData: List<WeatherDataResponse.HourlyWeather>
    lateinit var dailyData: List<WeatherDataResponse.DailyWeather>

    lateinit var dt: String
    lateinit var temp: String
    lateinit var pressure: String
    lateinit var humidity: String
    lateinit var visibility: String

    fun weatherData() {
        viewModelScope.launch {
            try {
                val weatherData = WeatherDataApi.retrofitService.getWeatherData(
                    latLiveData.value,
                    lonLiveData.value,
                    "metric",
                    "1dfb4f777e8166a625871672bfe34c06"
                )

                currentData = weatherData.current
                hourlyData = weatherData.hourly
                dailyData = weatherData.daily

                dt = currentData.dt.toString()
                temp = currentData.temp.toString()
                pressure = currentData.pressure.toString()
                humidity = currentData.humidity.toString()
                visibility = currentData.visibility.toString()
            } catch (_: Exception) {

            }
        }
    }
}
