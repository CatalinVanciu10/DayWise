package org.daywise.com

import androidx.compose.ui.window.ComposeUIViewController
import com.task.businesslogicshared.di.businessModule
import org.daywise.com.di.AppModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController {
    startKoin {
        modules(listOf(AppModule.appModule, businessModule))
    }
    App()
}
