package com.task.businesslogicshared.viewmodel

import com.task.businesslogicshared.domain.model.WeatherUiState
import com.task.businesslogicshared.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class IOSWeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase
) : WeatherViewModel {

    private val scope: CoroutineScope = MainScope()

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    override val uiState: StateFlow<WeatherUiState> = _uiState

    override fun loadWeather(city: String) {
        scope.launch {
            val weather = getWeatherUseCase(city)
            _uiState.update {
                WeatherUiState.Success(weather)
            }
        }
    }
}
