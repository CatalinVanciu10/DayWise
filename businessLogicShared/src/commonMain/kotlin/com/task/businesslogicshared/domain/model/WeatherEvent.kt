package com.task.businesslogicshared.domain.model

sealed class WeatherEvent {
    data class OnGetWeather(val city: String) : WeatherEvent()
}
