package com.example.weather_clean_flow.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Temp(
    @Json(name = "day")
    val day: Double,
    @Json(name = "eve")
    val eve: Double,
    @Json(name = "max")
    val max: Double,
    @Json(name = "min")
    val min: Double,
    @Json(name = "morn")
    val morn: Double,
    @Json(name = "night")
    val night: Double
)