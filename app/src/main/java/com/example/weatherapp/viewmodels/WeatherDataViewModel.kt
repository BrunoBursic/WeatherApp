package com.example.weatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.network.WeatherDataApi
import com.example.weatherapp.network.WeatherDataResponse
import kotlinx.coroutines.launch

class WeatherDataViewModel(private val coordinatesViewModel: CoordinatesViewModel) : ViewModel() {
    //https://medium.com/@summitkumar/sharing-data-between-viewmodels-in-androids-mvvm-architecture-56a5009e4e63
    //link za share-anje podataka između viewmodela

    private val latLiveData: LiveData<Double> = coordinatesViewModel.getLat()
    private val lonLiveData: LiveData<Double> = coordinatesViewModel.getLon()
    val cityNameLiveData: LiveData<String> = coordinatesViewModel.getcityName()

    /*private val _currentData = MutableLiveData<WeatherDataResponse.CurrentWeather>()
    val currentData: LiveData<WeatherDataResponse.CurrentWeather>
                get() = _currentData*/
    private val _hourlyData = MutableLiveData<List<WeatherDataResponse.HourlyWeather>>()
    val hourlyData: LiveData<List<WeatherDataResponse.HourlyWeather>>
        get() = _hourlyData
    private val _dailyData = MutableLiveData<List<WeatherDataResponse.DailyWeather>>()
    val dailyData: LiveData<List<WeatherDataResponse.DailyWeather>>
        get() = _dailyData
    //-------------------------------------------------------------
    private val _dt = MutableLiveData<String>()
    val dt: LiveData<String>
        get() = _dt
    private val _temp = MutableLiveData<String>()
    val temp: LiveData<String>
        get() = _temp
    private val _pressure = MutableLiveData<String>()
    val pressure: LiveData<String>
        get() = _pressure
    private val _humidity = MutableLiveData<String>()
    val humidity: LiveData<String>
        get() = _humidity
    private val _visibility = MutableLiveData<String>()
    val visibility: LiveData<String>
        get() = _visibility
    private val _text = MutableLiveData<String>()
    val text: LiveData<String>
        get() = _text

    fun weatherData() {
        viewModelScope.launch {
            try {
                val weatherData = WeatherDataApi.retrofitService.getWeatherData(
                    latLiveData.value,
                    lonLiveData.value,
                    "metric",
                    "1dfb4f777e8166a625871672bfe34c06"
                )

                //_currentData.value = weatherData.current
                _hourlyData.value = weatherData.hourly
                _dailyData.value = weatherData.daily

                _dt.value = weatherData.current.dt.toString()
                _temp.value = weatherData.current.temp.toString()
                _pressure.value = weatherData.current.pressure.toString()
                _humidity.value = weatherData.current.humidity.toString()
                _visibility.value = weatherData.current.visibility.toString()

                _text.value = "${dt}\n${temp}\n${pressure}\n${humidity}\n${visibility}"
            } catch (e: Exception) {
                _text.value = "Exception: ${e.message}"
            }
        }
    }
}
