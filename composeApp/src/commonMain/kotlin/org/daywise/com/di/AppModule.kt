package org.daywise.com.di

import org.daywise.com.Greeting
import org.daywise.com.weather.WeatherViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val appModule = module {
        single { Greeting().greet() }
        viewModel { WeatherViewModel(get(), get()) }
    }
}
