package com.task.businesslogicshared.domain.usecase

import com.task.businesslogicshared.domain.repository.GeminiRepository

class GetDressingAdviceUseCase(
    private val geminiRepository: GeminiRepository
) {
    suspend fun execute(
        prompt: String,
        temperature: Double = 0.7,
        maxOutputTokens: Int = 100
    ): String = geminiRepository.generateContent(
        prompt = prompt,
        temperature = temperature,
        maxOutputTokens = maxOutputTokens
    )
}
