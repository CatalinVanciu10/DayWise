package com.task.businesslogicshared.domain.repository

interface GeminiRepository {
    suspend fun generateContent(
        prompt: String,
        temperature: Double = 0.7,
        maxOutputTokens: Int = 100
    ): String
}
