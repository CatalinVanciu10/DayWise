package com.task.businesslogicshared.di

import com.task.businesslogicshared.data.network.ApiService
import com.task.businesslogicshared.data.repository.WeatherRepositoryImpl
import com.task.businesslogicshared.domain.repository.WeatherRepository
import com.task.businesslogicshared.domain.usecase.GetWeatherUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val businessModule = module {
    single {
        HttpClient(getHttpClientEngine()) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true; coerceInputValues = true })
            }
        }
    }
    single { ApiService(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single { GetWeatherUseCase(get()) }
}
