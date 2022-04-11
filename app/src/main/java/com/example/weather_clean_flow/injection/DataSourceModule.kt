package com.example.weather_clean_flow.injection

import com.example.weather_clean_flow.data.datasource.remote.RemoteDataSource
import com.example.weather_clean_flow.data.datasource.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun remoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}