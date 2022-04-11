package com.example.weather_clean_flow.data.repository

import com.example.weather_clean_flow.common.util.ApiResult
import com.example.weather_clean_flow.data.datasource.remote.RemoteDataSource
import com.example.weather_clean_flow.data.mapper.MapperImpl
import com.example.weather_clean_flow.data.network.model.WeatherResponse
import com.example.weather_clean_flow.domain.model.Weather
import com.example.weather_clean_flow.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : WeatherRepository {
    override suspend fun getWeatherForecast(): Flow<ApiResult<List<Weather>>> = flow {
        emit(convert(remoteDataSource.getWeatherForecast()))
    }.flowOn(Dispatchers.IO)

    private suspend fun convert(response: Response<WeatherResponse>): ApiResult<List<Weather>> {
        if (response.isSuccessful) {
            response.body()?.let {
                val weathers = it.weatherDataEntities.map { weatherDataEntity ->
                    MapperImpl.mapWeatherDataEntity(weatherDataEntity)
                }
                return ApiResult.Success(data = weathers)
            }
        }
        return ApiResult.Error(errorMessage = response.message())
    }
}