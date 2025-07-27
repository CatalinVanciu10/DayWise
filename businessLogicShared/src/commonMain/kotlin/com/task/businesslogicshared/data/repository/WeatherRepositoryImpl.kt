package com.task.businesslogicshared.data.repository

import com.task.businesslogicshared.data.mapper.WeatherMapper
import com.task.businesslogicshared.data.network.ApiService
import com.task.businesslogicshared.domain.model.Weather
import com.task.businesslogicshared.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val apiService: ApiService
) : WeatherRepository {
    override suspend fun getWeather(city: String): Weather {
        val response = apiService.fetchWeather(city)
        return WeatherMapper.toDomain(response)
    }
}
