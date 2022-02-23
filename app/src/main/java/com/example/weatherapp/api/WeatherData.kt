package com.example.weatherapp.api

import com.squareup.moshi.Json


data class WeatherData(
    @Json(name = "location") val location: LocationData,
    @Json(name = "current") val current: CurrentData,
) {

    data class LocationData(
        @Json(name = "name") val name: String,
        @Json(name = "country") val country: String,
    )

    data class CurrentData(
        @Json(name = "temp_c") val temp: Double,
        @Json(name = "condition") val name: ConditionData,
        @Json(name = "wind_kph") val wind: Double,
        @Json(name = "humidity") val humidity: Double,
    ) {

        data class ConditionData(
            @Json(name = "text") val text: String,
            @Json(name = "icon") val icon: String,
        )
    }

}