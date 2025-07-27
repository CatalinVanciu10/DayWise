package com.task.businesslogicshared.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.businesslogicshared.domain.model.WeatherUiState
import com.task.businesslogicshared.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AndroidWeatherViewModel(private val useCase: GetWeatherUseCase) : ViewModel(), WeatherViewModel {
    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    override val uiState: StateFlow<WeatherUiState> = _uiState


    override fun loadWeather(city: String) {
        _uiState.value = WeatherUiState.Loading
        viewModelScope.launch {
            try {
                val result = useCase(city)
                _uiState.value = WeatherUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
