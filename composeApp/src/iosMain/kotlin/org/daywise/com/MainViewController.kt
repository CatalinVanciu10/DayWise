package org.daywise.com

import androidx.compose.ui.window.ComposeUIViewController
import com.task.businesslogicshared.di.businessModule
import org.daywise.com.di.AppModule
import org.daywise.com.permissions.setupIosNotificationsDelegate
import org.koin.core.context.startKoin
import platform.EventKit.EKEntityType
import platform.EventKit.EKEventStore
import platform.UserNotifications.UNAuthorizationOptionAlert
import platform.UserNotifications.UNAuthorizationOptionBadge
import platform.UserNotifications.UNAuthorizationOptionSound
import platform.UserNotifications.UNUserNotificationCenter

fun MainViewController() = ComposeUIViewController {
    startKoin {
        modules(listOf(AppModule.appModule, businessModule))
    }
    val center = UNUserNotificationCenter.currentNotificationCenter()
    val eventStore = EKEventStore()

    center.requestAuthorizationWithOptions(
        UNAuthorizationOptionAlert or UNAuthorizationOptionSound or UNAuthorizationOptionBadge
    ) { granted, error ->
        println("🔔 Notification permission: ${if (granted) "GRANTED" else "DENIED"}")

        eventStore.requestAccessToEntityType(EKEntityType.EKEntityTypeEvent) { calendarGranted, _ ->
            println("📅 Calendar permission: ${if (calendarGranted) "GRANTED" else "DENIED"}")
        }
    }
    setupIosNotificationsDelegate()
    App()
}
