package com.task.businesslogicshared.data.repository

import com.task.businesslogicshared.data.network.ApiService
import com.task.businesslogicshared.domain.repository.GeminiRepository

class GeminiRepositoryImpl(
    private val apiService: ApiService
) : GeminiRepository {
    override suspend fun generateContent(
        prompt: String,
        temperature: Double,
        maxOutputTokens: Int
    ): String {
        return apiService.generateContent(
            prompt = prompt,
            temperature = temperature,
            maxOutputTokens = maxOutputTokens
        )
    }
}
