package com.task.businesslogicshared.data.network

import com.task.businesslogicshared.data.model.Content
import com.task.businesslogicshared.data.model.GeminiRequest
import com.task.businesslogicshared.data.model.GeminiResponse
import com.task.businesslogicshared.data.model.GenerationConfig
import com.task.businesslogicshared.data.model.Part
import com.task.businesslogicshared.data.model.WeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ApiService(private val client: HttpClient) {
    private val weatherApiKey = "YOUR_API_KEY"
    private val geminiApiKey = "YOUR_GEMINI_API_KEY"
    private val baseUrl = "https://generativelanguage.googleapis.com"
    private val modelName = "gemini-1.5-flash"

    suspend fun fetchWeather(city: String): WeatherResponse {
        return client.get("https://api.openweathermap.org/data/2.5/weather") {
            parameter("q", city)
            parameter("appid", weatherApiKey)
        }.body()
    }

    suspend fun generateContent(
        prompt: String,
        temperature: Double = 0.7,
        maxOutputTokens: Int = 100
    ): String {
        val requestBody = GeminiRequest(
            contents = listOf(
                Content(
                    role = "user",
                    parts = listOf(Part(text = prompt))
                )
            ),
            generationConfig = GenerationConfig(
                temperature = temperature,
                maxOutputTokens = maxOutputTokens
            )
        )

        val response: GeminiResponse = client.post("$baseUrl/v1beta/models/$modelName:generateContent?key=$geminiApiKey") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }.body()

        return response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text ?: "Nu s-a putut genera un răspuns."
    }

}
