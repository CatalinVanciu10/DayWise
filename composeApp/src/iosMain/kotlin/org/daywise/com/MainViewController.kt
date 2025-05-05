package org.daywise.com

import androidx.compose.ui.window.ComposeUIViewController
import org.daywise.com.di.AppModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController {
    startKoin {
        modules(AppModule.appModule)
    }
    App()
}
