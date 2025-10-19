package org.daywise.com

import androidx.compose.ui.window.ComposeUIViewController
import com.task.businesslogicshared.di.businessModule
import org.daywise.com.di.AppModule
import org.daywise.com.permissions.NotificationScreen
import org.daywise.com.permissions.rememberNotificationManager
import org.daywise.com.permissions.setupIosNotificationsDelegate
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController {
    startKoin {
        modules(listOf(AppModule.appModule, businessModule))
    }
//    App()
    setupIosNotificationsDelegate()
    NotificationScreen(rememberNotificationManager())
}
