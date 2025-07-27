package com.task.businesslogicshared.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MainInfo(
    val temp: Double,
    val humidity: Int
)
