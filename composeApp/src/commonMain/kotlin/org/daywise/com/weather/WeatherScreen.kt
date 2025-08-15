package org.daywise.com.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.task.businesslogicshared.domain.model.Weather
import org.daywise.com.DayWiseTheme
import org.daywise.com.common.AppUIState
import org.daywise.com.common.BouncingDotsLoading
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = koinViewModel()) {

    WeatherAiScreen(
        viewModel = viewModel
    )
}


@Composable
fun WeatherAiScreen(
    viewModel: WeatherViewModel,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(Unit) {
        viewModel.loadWeather("Timisoara")
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when (uiState.appState) {
            is AppUIState.Loading -> {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }

            is AppUIState.Error -> {
                Text(
                    text = (uiState.appState as AppUIState.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
            }

            is AppUIState.Success -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = uiState.city,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Text(
                        text = "${uiState.temperature} °C",
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Text(
                        text = uiState.description,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Icon(
                        imageVector = Icons.Default.WbSunny,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(80.dp)
                    )

                    ElevatedCard(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lightbulb,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSecondary,
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))

                            if (uiState.loadingDressingAdvice) {
                                BouncingDotsLoading()
                            } else {
                                Text(
                                    text = uiState.dressingAdvice,
                                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                                    color = MaterialTheme.colorScheme.onSecondary
                                )
                            }

                        }
                    }

                    Button(
                        onClick = {
                            viewModel.generateDressingAdvice(
                                Weather(
                                    city = uiState.city,
                                    temperatureCelsius = uiState.temperature.toDouble(),
                                    description = uiState.description
                                )
                            )
                        },
                        shape = RoundedCornerShape(50)
                    ) {
                        Text("Ask Personal Assistant")
                    }
                }
            }
        }
    }
}

