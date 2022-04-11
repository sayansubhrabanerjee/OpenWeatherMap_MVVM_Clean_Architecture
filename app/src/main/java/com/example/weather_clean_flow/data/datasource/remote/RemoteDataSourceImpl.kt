package com.example.weather_clean_flow.data.datasource.remote

import com.example.weather_clean_flow.data.network.apiservice.WeatherService
import com.example.weather_clean_flow.data.network.model.WeatherResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val weatherService: WeatherService
) : RemoteDataSource {
    override suspend fun getWeatherForecast(): Response<WeatherResponse> =
        weatherService.getWeatherForecast(dayCount = 16)
}