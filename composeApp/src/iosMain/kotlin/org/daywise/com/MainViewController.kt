package org.daywise.com

import androidx.compose.ui.window.ComposeUIViewController
import com.task.businesslogicshared.di.businessModule
import org.daywise.com.di.AppModule
import org.daywise.com.permissions.NotificationScreen
import org.daywise.com.permissions.rememberNotificationManager
import org.daywise.com.permissions.setupIosNotificationsDelegate
import org.koin.core.context.startKoin
import platform.UserNotifications.UNAuthorizationOptionAlert
import platform.UserNotifications.UNAuthorizationOptionBadge
import platform.UserNotifications.UNAuthorizationOptionSound
import platform.UserNotifications.UNUserNotificationCenter

fun MainViewController() = ComposeUIViewController {
    startKoin {
        modules(listOf(AppModule.appModule, businessModule))
    }
//    App()
    val center = UNUserNotificationCenter.currentNotificationCenter()
    center.requestAuthorizationWithOptions(
        UNAuthorizationOptionAlert or UNAuthorizationOptionSound or UNAuthorizationOptionBadge
    ) { granted, error ->
        if (error != null) {
            println("❌ Error: ${error.localizedDescription}")
        } else {
            println("🔐 Authorization granted: $granted")
        }
    }
    setupIosNotificationsDelegate()
    App()
}
