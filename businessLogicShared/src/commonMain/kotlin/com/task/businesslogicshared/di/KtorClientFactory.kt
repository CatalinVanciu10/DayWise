package com.task.businesslogicshared.di

import io.ktor.client.engine.HttpClientEngine

expect fun getHttpClientEngine(): HttpClientEngine
