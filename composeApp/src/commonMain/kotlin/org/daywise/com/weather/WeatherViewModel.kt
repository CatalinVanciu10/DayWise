package org.daywise.com.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.businesslogicshared.domain.model.Weather
import com.task.businesslogicshared.domain.model.WeatherUiState
import com.task.businesslogicshared.domain.usecase.GetDressingAdviceUseCase
import com.task.businesslogicshared.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getDressingAdviceUseCase: GetDressingAdviceUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState: StateFlow<WeatherUiState> = _uiState

    fun loadWeather(city: String) {
        _uiState.value = WeatherUiState.Loading
        viewModelScope.launch {
            try {
                val result = getWeatherUseCase(city)
                generateDressingAdvice(result)
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun generateDressingAdvice(weather: Weather) {
        val prompt = """
                    Ești un stilist personal și oferi sfaturi concise de îmbrăcăminte bazate pe vreme.
                    Vremea actuală în ${weather.city} este:
                    - Temperatură: ${weather.temperatureCelsius}°C
                    - Descriere: ${weather.description}.
                    
                    Pe baza acestor informații, ce ar trebui să port astăzi? Fii scurt, precis și oferă o singură sugestie practică.
                    """
            .trimIndent()
        viewModelScope.launch {
            try {
                val result = getDressingAdviceUseCase.execute(
                    prompt = prompt
                )
                _uiState.value = WeatherUiState.Success(result)
            } catch (exception: Exception) {
                _uiState.value = WeatherUiState.Error(exception.message ?: "Unknown error")
            }
        }
    }
}
