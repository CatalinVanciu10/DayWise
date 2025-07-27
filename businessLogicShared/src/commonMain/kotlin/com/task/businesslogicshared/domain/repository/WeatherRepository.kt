package com.task.businesslogicshared.domain.repository

import com.task.businesslogicshared.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeather(city: String): Weather
}
