package com.example.weather_clean_flow.data.mapper

import com.example.weather_clean_flow.data.network.model.WeatherDataEntity
import com.example.weather_clean_flow.domain.model.Weather

object MapperImpl : Mapper {
    override suspend fun mapWeatherDataEntity(weatherDataEntity: WeatherDataEntity): Weather =
        Weather(
            id = weatherDataEntity.weatherEntities.map { it.id },
            main = weatherDataEntity.weatherEntities.map { it.main },
            description = weatherDataEntity.weatherEntities.map { it.description },
            icon = weatherDataEntity.weatherEntities.map { it.icon },
            maxTemp = weatherDataEntity.temp.max,
        )
}