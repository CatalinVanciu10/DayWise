package org.daywise.com.permissions

import androidx.compose.runtime.Composable

@Composable
actual fun rememberNotificationManager(): NotificationManager {
    error("Use App(notificationManager) injected from MainActivity on Android.")
}
