package com.example.weather_clean_flow.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeelsLike(
    @Json(name = "day")
    val day: Double,
    @Json(name = "eve")
    val eve: Double,
    @Json(name = "morn")
    val morn: Double,
    @Json(name = "night")
    val night: Double
)