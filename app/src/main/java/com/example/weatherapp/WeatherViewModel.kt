package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.network.CoordinatesApi
import com.example.weatherapp.network.CoordinatesApiService
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _citydata = MutableLiveData<String>()
    val citydata: LiveData<String>
        get() = _citydata

    fun coordinates(city: String) {
        viewModelScope.launch {
            try {
                val coordinates = CoordinatesApi.retrofitService.getLocationData(
                    city,
                    5,
                    "1dfb4f777e8166a625871672bfe34c06"
                )
                _citydata.value = "Name: ${coordinates[0].name}\n " +
                        "Lon: ${coordinates[0].lon}\n" +
                        "Lat: ${coordinates[0].lat}" +
                        "Country: ${coordinates[0].country}\n" +
                        "State: ${coordinates[0].state}"
            } catch (e: Exception) {
                _citydata.value = "Failure: ${e.message}"
            }
        }
    }
}