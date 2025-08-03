package com.task.businesslogicshared.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GeminiResponse(
    val candidates: List<Candidate>,
    val promptFeedback: PromptFeedback? = null
)
