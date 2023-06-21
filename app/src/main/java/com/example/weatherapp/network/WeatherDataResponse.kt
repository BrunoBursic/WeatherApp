package com.example.weatherapp.network

data class WeatherDataResponse(
    val timezone: String,
    val current: CurrentWeather,
    val hourly: List<HourlyWeather>,
    val daily: List<DailyWeather>
) {
    data class CurrentWeather(
        val dt: Long,
        val temp: Float,
        val pressure: Float,
        val humidity: Float,
        val visibility: Float,
        //val weather: Weather
    )

    data class HourlyWeather(
        val dt: Long,
        val temp: Float,
        val pop: Float,
        //val weather: Weather
    )

    data class DailyWeather(
        val dt: Long,
        val temp: Temperature,
        val summary: String,
        val pop: Float,
        //val weather: Weather
    )

    data class Temperature(
        val morn: Float,
        val day: Float,
        val eve: Float,
        val night: Float
    )

    data class Weather(
        //val id: Int,
        //val main: String,
        val description: String,
        val icon: String
    )
}