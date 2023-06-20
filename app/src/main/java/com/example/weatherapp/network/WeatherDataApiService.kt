package com.example.weatherapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface WeatherDataApiService {
    //funkcija za api poziv
    @GET("data/3.0/onecall")
    suspend fun getWeatherData(
        @Query("lat") lat: Double?,
        @Query("lon") lon: Double?,
        @Query("units") units: String,
        @Query("appid") appId: String
    ): WeatherDataResponse
}

object WeatherDataApi {
    val retrofitService: WeatherDataApiService by lazy {retrofit.create(WeatherDataApiService::class.java)}
}