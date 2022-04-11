package com.example.weather_clean_flow.data.mapper

import com.example.weather_clean_flow.data.network.model.WeatherDataEntity
import com.example.weather_clean_flow.domain.model.Weather

interface Mapper {
    suspend fun mapWeatherDataEntity(weatherDataEntity: WeatherDataEntity): Weather
}