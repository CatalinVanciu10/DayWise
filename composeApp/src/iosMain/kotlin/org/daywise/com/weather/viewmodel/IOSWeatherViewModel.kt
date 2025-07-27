package org.daywise.com.weather.viewmodel

import com.task.businesslogicshared.domain.model.WeatherEvent
import com.task.businesslogicshared.domain.usecase.GetWeatherUseCase
import com.task.businesslogicshared.viewmodel.IOSWeatherViewModel
import com.task.businesslogicshared.viewmodel.WeatherViewModel

class IOSWeatherViewModel(
    getWeatherUseCase: GetWeatherUseCase
) : WeatherViewModel {
    private val impl = IOSWeatherViewModel(getWeatherUseCase)
    override val uiState = impl.uiState

    fun onEvent(event: WeatherEvent) = when (event) {
        is WeatherEvent.OnGetWeather -> {
            loadWeather(event.city)
        }
    }

    override fun loadWeather(city: String) = impl.loadWeather(city)
}
