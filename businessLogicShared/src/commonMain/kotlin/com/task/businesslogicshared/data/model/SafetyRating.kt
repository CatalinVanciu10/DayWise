package com.task.businesslogicshared.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SafetyRating(
    val category: String,
    val probability: String
)
