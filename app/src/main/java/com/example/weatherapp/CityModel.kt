package com.example.weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.WeatherApi
import com.example.weatherapp.api.WeatherData
import kotlinx.coroutines.launch

val cities: List<String> = listOf(
    "Lyon",
    "Dijon",
    "Montpellier"
)

private const val TAG = "CITY_MODEL"

enum class Result { WAITING, ERROR, SUCCESS }

class CityModel : ViewModel() {

    private val _citiesData = MutableLiveData<MutableMap<String, WeatherData>>()
    val citiesData: LiveData<MutableMap<String, WeatherData>> = _citiesData

    private val _test = MutableLiveData<String>()
    val test: LiveData<String> = _test

    lateinit var result: Result

    init {
        getWeatherCity()
    }

    private fun getWeatherCity() {
        result = Result.WAITING
        try {
            viewModelScope.launch {
                for (city in cities) {
                    val data = WeatherApi.retrofitService.getWeatherDataOnCity(q = city)

                    _test.value = "Succes: ${data} elements"
                    // val data = WeatherApi.retrofitService.getWeatherDataOnCity(q = city)
                    // _citiesData.value?.set(city, data)
                    Log.d(TAG, "got ${data}")
                }
            }
            result = Result.SUCCESS
        } catch (e: Exception) {
            result = Result.ERROR
            Log.d(TAG, "got error $e")
        }
    }
}