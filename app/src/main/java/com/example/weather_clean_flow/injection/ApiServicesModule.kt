package com.example.weather_clean_flow.injection

import com.example.weather_clean_flow.data.network.apiservice.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiServicesModule {

    @Provides
    fun weatherService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)
}