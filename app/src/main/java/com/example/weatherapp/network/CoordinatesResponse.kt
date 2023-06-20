package com.example.weatherapp.network

data class CoordinatesResponse (
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String
    )