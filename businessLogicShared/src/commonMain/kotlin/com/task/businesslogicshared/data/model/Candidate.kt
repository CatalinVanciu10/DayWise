package com.task.businesslogicshared.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Candidate(
    val content: Content,
    val finishReason: String? = null,
    val safetyRatings: List<SafetyRating>? = null
)
