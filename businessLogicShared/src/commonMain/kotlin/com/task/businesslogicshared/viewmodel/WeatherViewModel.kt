package com.task.businesslogicshared.viewmodel

import com.task.businesslogicshared.domain.model.WeatherUiState
import kotlinx.coroutines.flow.StateFlow

interface WeatherViewModel {
    val uiState: StateFlow<WeatherUiState>
    fun loadWeather(city: String)
}
