package com.task.businesslogicshared.domain.model

sealed class WeatherUiState {
    object Loading : WeatherUiState()
    data class Success(val geminiResult: String) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}
