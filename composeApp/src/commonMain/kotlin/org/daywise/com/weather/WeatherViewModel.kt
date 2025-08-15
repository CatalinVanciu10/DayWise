package org.daywise.com.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.businesslogicshared.domain.model.Weather
import com.task.businesslogicshared.domain.usecase.GetDressingAdviceUseCase
import com.task.businesslogicshared.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.daywise.com.common.AppUIState
import kotlin.math.roundToInt

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getDressingAdviceUseCase: GetDressingAdviceUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<WeatherScreenView> = MutableStateFlow(WeatherScreenView())
    val uiState: StateFlow<WeatherScreenView> = _uiState.asStateFlow()

    fun loadWeather(city: String) {
        viewModelScope.launch {
            try {
                val result = getWeatherUseCase(city)
                _uiState.update {
                    it.copy(
                        city = result.city,
                        temperature = result.temperatureCelsius.roundToInt().toString(),
                        description = result.description,
                        appState = AppUIState.Success
                    )
                }
//                generateDressingAdvice(result)
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        appState = AppUIState.Error(e.message ?: "Unknown error")
                    )
                }
            }
        }
    }

    fun generateDressingAdvice(weather: Weather) {
        val prompt = """
                    Ești un stilist personal și oferi sfaturi concise de îmbrăcăminte bazate pe vreme.
                    Vremea actuală în ${weather.city} este:
                    - Temperatură: ${weather.temperatureCelsius}°C
                    - Descriere: ${weather.description}.
                    
                    Pe baza acestor informații, ce ar trebui să port astăzi? Fii scurt, precis și oferă o singură sugestie practică.
                    """
            .trimIndent()
        _uiState.update {
            it.copy(
                loadingDressingAdvice = true
            )
        }
        viewModelScope.launch {
            try {
                val result = getDressingAdviceUseCase.execute(
                    prompt = prompt
                )
                _uiState.update {
                    it.copy(
                        dressingAdvice = result,
                        loadingDressingAdvice = false,
                        appState = AppUIState.Success
                    )
                }
            } catch (exception: Exception) {
                _uiState.update {
                    it.copy(
                        appState = AppUIState.Error(exception.message ?: "Unknown error")
                    )
                }
            }
        }
    }
}

data class WeatherScreenView(
    val appState: AppUIState = AppUIState.Loading,
    val city: String = "",
    val temperature: String = "",
    val description: String = "",
    val loadingDressingAdvice: Boolean = false,
    val dressingAdvice: String = ""
)
