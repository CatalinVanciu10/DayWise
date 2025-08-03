package com.task.businesslogicshared.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PromptFeedback(
    val safetyRatings: List<SafetyRating>? = null
)
