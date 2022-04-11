package com.example.weather_clean_flow.domain.usecase

import com.example.weather_clean_flow.domain.model.Weather
import com.example.weather_clean_flow.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetWeatherForecastUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    //suspend fun getWeatherForecast(): Flow<ApiResult<List<Weather>>> = weatherRepository.getWeatherForecast()

    suspend fun getWeatherForecast(): Flow<List<Weather>> =
        weatherRepository.getWeatherForecast().map {
            it.data!!.sortedByDescending { weather -> weather.maxTemp }
        }
}
