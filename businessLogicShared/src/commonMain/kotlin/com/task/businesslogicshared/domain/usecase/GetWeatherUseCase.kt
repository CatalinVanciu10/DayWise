package com.task.businesslogicshared.domain.usecase

import com.task.businesslogicshared.domain.model.Weather
import com.task.businesslogicshared.domain.repository.WeatherRepository


class GetWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(city: String): Weather {
        return repository.getWeather(city)
    }
}
