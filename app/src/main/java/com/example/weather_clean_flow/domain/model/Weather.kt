package com.example.weather_clean_flow.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val id: List<Int>,
    val main: List<String>,
    val description: List<String>,
    val icon: List<String>,
    val maxTemp: Double,
) : Parcelable
