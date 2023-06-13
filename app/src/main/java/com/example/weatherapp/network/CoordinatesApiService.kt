package com.example.weatherapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "http://api.openweathermap.org/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CoordinatesApiService {
    @GET("geo/1.0/direct")
    suspend fun getLocationData(
        @Query("q") q: String,
        @Query("limit") limit: Int,
        @Query("appid") appId: String
    ): List<CoordinatesResponse>
}

object CoordinatesApi {
    val retrofitService: CoordinatesApiService by lazy { retrofit.create(CoordinatesApiService::class.java) }
}