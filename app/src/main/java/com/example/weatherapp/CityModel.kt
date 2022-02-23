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

enum class WeatherApiResult { WAITING, ERROR, SUCCESS }

class CityModel : ViewModel() {

    private val _citiesData = MutableLiveData<MutableList<WeatherData>>()
    val citiesData: LiveData<MutableList<WeatherData>> = _citiesData

    private val _test = MutableLiveData<String>()
    val test: LiveData<String> = _test

    private val _result = MutableLiveData<WeatherApiResult>()
    val result: LiveData<WeatherApiResult> = _result

    init {
        getWeatherCity()
    }

    private fun getWeatherCity() {
        _result.value = WeatherApiResult.WAITING
        try {
            viewModelScope.launch {
                val allData: MutableList<WeatherData> = mutableListOf<WeatherData>()

                for (city in cities) {
                    allData.add(WeatherApi.retrofitService.getWeatherDataOnCity(q = city))
                }
                _citiesData.value = allData
                _result.value = WeatherApiResult.SUCCESS
            }
        } catch (e: Exception) {
            _result.value = WeatherApiResult.ERROR
            Log.d(TAG, "got error $e")
        }
    }
}