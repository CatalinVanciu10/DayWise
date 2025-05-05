package org.daywise.com.di

import org.daywise.com.Greeting
import org.koin.dsl.module

object AppModule {
    val appModule = module {
        single { Greeting().greet() }
    }
}
