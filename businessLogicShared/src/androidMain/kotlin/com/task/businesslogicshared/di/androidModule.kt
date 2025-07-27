package com.task.businesslogicshared.di

import com.task.businesslogicshared.viewmodel.AndroidWeatherViewModel
import com.task.businesslogicshared.viewmodel.WeatherViewModel
import org.koin.dsl.module

val androidModule = module {
    factory<WeatherViewModel> { AndroidWeatherViewModel(get()) }
}
