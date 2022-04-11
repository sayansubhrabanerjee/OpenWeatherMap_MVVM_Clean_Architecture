package com.example.weather_clean_flow.data.datasource.remote

import com.example.weather_clean_flow.data.network.model.WeatherResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getWeatherForecast(): Response<WeatherResponse>
}