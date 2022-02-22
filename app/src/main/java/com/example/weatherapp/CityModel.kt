package com.example.weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG = "CITY_MODEL"

class CityModel: ViewModel() {

    private val _test = MutableLiveData<String>()
    val test: LiveData<String> = _test

    init {
        _test.value = "This is my default value"
    }

    fun setTestValueToAnotherValue() {
        Log.d(TAG, "test value before: $test")
        _test.value = "Nice try bro!"
        Log.d(TAG, "test value after: $test")
    }
}