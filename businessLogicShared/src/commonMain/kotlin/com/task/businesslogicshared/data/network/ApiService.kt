package com.task.businesslogicshared.data.network

import com.task.businesslogicshared.data.model.WeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiService(private val client: HttpClient) {
    private val apiKey = "YOUR_API_KEY"

    suspend fun fetchWeather(city: String): WeatherResponse {
        return client.get("https://api.openweathermap.org/data/2.5/weather") {
            parameter("q", city)
            parameter("appid", apiKey)
        }.body()
    }
}
