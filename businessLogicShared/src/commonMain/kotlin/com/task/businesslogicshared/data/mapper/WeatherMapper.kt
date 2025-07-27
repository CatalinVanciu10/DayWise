package com.task.businesslogicshared.data.mapper

import com.task.businesslogicshared.data.model.WeatherResponse
import com.task.businesslogicshared.domain.model.Weather

object WeatherMapper {
    fun toDomain(response: WeatherResponse): Weather {
        val desc = response.weather.firstOrNull()?.description ?: "No description"
        // OpenWeatherMap returns temperature in Kelvin by default, transform to Celsius:
        val tempCelsius = response.main.temp - 273.15
        return Weather(
            city = response.cityName,
            temperatureCelsius = tempCelsius,
            description = desc
        )
    }
}
