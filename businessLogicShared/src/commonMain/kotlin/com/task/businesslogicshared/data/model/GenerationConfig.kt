package com.task.businesslogicshared.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GenerationConfig(
    val temperature: Double? = null,
    val topK: Int? = null,
    val topP: Double? = null,
    val maxOutputTokens: Int? = null,
    val stopSequences: List<String>? = null
)
