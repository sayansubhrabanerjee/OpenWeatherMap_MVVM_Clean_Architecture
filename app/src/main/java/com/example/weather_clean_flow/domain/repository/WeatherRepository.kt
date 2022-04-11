package com.example.weather_clean_flow.domain.repository

import com.example.weather_clean_flow.common.util.ApiResult
import com.example.weather_clean_flow.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherForecast(): Flow<ApiResult<List<Weather>>>
}