package com.task.businesslogicshared.di

import com.task.businesslogicshared.viewmodel.IOSWeatherViewModel
import com.task.businesslogicshared.viewmodel.WeatherViewModel
import org.koin.dsl.module

val iosModule = module {
    factory<WeatherViewModel> { IOSWeatherViewModel(get()) }
}
