package com.example.weatherapp.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.weatherapi.com/v1/"


private const val API_KEY = "147d4ae3304f42d981a190831222102"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface WeatherService {

    @GET("current.json")
    suspend fun getWeatherDataOnCity(@Query("key") key: String = API_KEY, @Query("q") q: String): WeatherData
}

object WeatherApi {
    val retrofitService: WeatherService by lazy {
        retrofit.create(WeatherService::class.java)
    }
}