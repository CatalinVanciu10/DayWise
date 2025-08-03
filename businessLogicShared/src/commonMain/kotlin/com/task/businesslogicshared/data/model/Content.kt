package com.task.businesslogicshared.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Content(
    val role: String,
    val parts: List<Part>
)
