package com.example.weather_clean_flow.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_clean_flow.common.util.UiStates
import com.example.weather_clean_flow.domain.model.Weather
import com.example.weather_clean_flow.domain.usecase.GetWeatherForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherForecastUseCase: GetWeatherForecastUseCase
) : ViewModel() {
    private val _uiStates = MutableStateFlow<UiStates>(UiStates.Empty)
    val uiStates: StateFlow<UiStates> = _uiStates.asStateFlow()

    private var _weather = MutableStateFlow<Weather?>(null)
    val weather: StateFlow<Weather?> = _weather.asStateFlow()

    init {
        getWeatherForecast()
    }

    private fun getWeatherForecast() = viewModelScope.launch {
        _uiStates.value = UiStates.Loading
        weatherForecastUseCase.getWeatherForecast()
            .catch {
                _uiStates.value = UiStates.Error(it)
            }.collectLatest {
                _uiStates.value = UiStates.Success(it)
            }
    }

    fun setWeather(weather: Weather) {
        _weather.value = weather
    }
}