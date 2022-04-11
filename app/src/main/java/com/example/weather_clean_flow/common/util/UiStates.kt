package com.example.weather_clean_flow.common.util

import com.example.weather_clean_flow.domain.model.Weather

sealed class UiStates {
    object Empty : UiStates()
    object Loading : UiStates()
    data class Success(val weathers: List<Weather>) : UiStates()
    data class Error(val throwable: Throwable?) : UiStates()
}