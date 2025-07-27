package com.task.businesslogicshared.data.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherInfo(
    val main: String,
    val description: String
)
