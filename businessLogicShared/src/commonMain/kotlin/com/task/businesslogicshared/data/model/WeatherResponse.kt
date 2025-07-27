package com.task.businesslogicshared.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("name")
    val cityName: String,
    val main: MainInfo,
    val weather: List<WeatherInfo>
)
