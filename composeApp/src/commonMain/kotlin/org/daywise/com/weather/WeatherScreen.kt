package org.daywise.com.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.task.businesslogicshared.domain.model.WeatherUiState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = koinViewModel()) {

    LaunchedEffect(Unit) {
        viewModel.loadWeather("Timisoara")
    }

    val state by viewModel.uiState.collectAsStateWithLifecycle()


    when (state) {
        is WeatherUiState.Loading -> {
            println("cata test: WEATHER IS LOADING")
        }

        is WeatherUiState.Success -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val weather = (state as WeatherUiState.Success).weather
                Text(text = weather.city)
                Text(text = weather.description)
                Text(text = weather.temperatureCelsius.toString())
            }
            println("cata test: WEATHER SUCCESS")
        }

        is WeatherUiState.Error -> {
            println("cata test: WEATHER FAILED: ${(state as WeatherUiState.Error).message}")
        }
    }

}
