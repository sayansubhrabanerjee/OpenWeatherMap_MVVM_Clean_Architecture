package com.example.weather_clean_flow.data.network.apiservice

import com.example.weather_clean_flow.BuildConfig
import com.example.weather_clean_flow.data.network.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    /*@GET("forecast/daily")
    suspend fun getWeatherForecast(
        @Query("lat") lat: Double = 12.97,
        @Query("lon") lon: Double = 77.59,
        @Query("cnt") dayCount: Int,
        @Query("appid") appid: String = BuildConfig.API_KEY,
    ): Response<WeatherResponse>*/

    @GET("forecast/daily")
    suspend fun getWeatherForecast(
        @Query("lat") lat: Double = 12.97,
        @Query("lon") lon: Double = 77.59,
        @Query("cnt") dayCount: Int,
        @Query("appid") appid: String = BuildConfig.API_KEY,
    ): Response<WeatherResponse>
}