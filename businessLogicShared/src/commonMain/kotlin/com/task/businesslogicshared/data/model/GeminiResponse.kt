package com.task.businesslogicshared.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GeminiResponse(
    val candidates: List<Candidate> = listOf(),
    val promptFeedback: PromptFeedback? = PromptFeedback(listOf())
)
