package com.example.weather_clean_flow.injection

import com.example.weather_clean_flow.data.repository.WeatherRepositoryImpl
import com.example.weather_clean_flow.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun weatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}