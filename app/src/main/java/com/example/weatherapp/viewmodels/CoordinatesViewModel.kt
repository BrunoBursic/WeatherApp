package com.example.weatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.network.CoordinatesApi
import kotlinx.coroutines.launch

class CoordinatesViewModel : ViewModel() {
    private val _citydata = MutableLiveData<String>()
    val citydata: LiveData<String>
        get() = _citydata

    private var lat = MutableLiveData<Double>()
    fun getLat() : LiveData<Double> {
        return lat
    }

    private var lon = MutableLiveData<Double>()
    fun getLon() : LiveData<Double> {
        return lon
    }

    private var cityName = MutableLiveData<String>()
    fun getcityName() : LiveData<String> {
        return cityName
    }

    fun coordinates(city: String) {
        viewModelScope.launch {
            try {
                val coordinates = CoordinatesApi.retrofitService.getLocationData(
                    city,
                    5,
                    "1dfb4f777e8166a625871672bfe34c06"
                )

                cityName.value = coordinates[0].name
                lon.value = coordinates[0].lon
                lat.value = coordinates[0].lat

                _citydata.value = "Name: ${coordinates[0].name}\n " +
                        "Lon: ${coordinates[0].lon}\n" +
                        "Lat: ${coordinates[0].lat}\n" +
                        "Country: ${coordinates[0].country}\n"
            } catch (e: Exception) {
                _citydata.value = "Failure: ${e.message}"
            }
        }
    }
}