package com.task.businesslogicshared.di

import com.task.businesslogicshared.data.repository.WeatherRepositoryImpl
import com.task.businesslogicshared.domain.repository.WeatherRepository
import com.task.businesslogicshared.domain.usecase.GetWeatherUseCase
import org.koin.dsl.module

val businessModule = module {
    single { GetWeatherUseCase(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
}
