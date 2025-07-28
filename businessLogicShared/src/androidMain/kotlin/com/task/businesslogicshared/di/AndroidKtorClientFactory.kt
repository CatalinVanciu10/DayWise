package com.task.businesslogicshared.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android

actual fun getHttpClientEngine(): HttpClientEngine = Android.create()
