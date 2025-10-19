package org.daywise.com.permissions

import androidx.compose.runtime.Composable

enum class PermissionStatus { GRANTED, DENIED }

interface NotificationManager {
    suspend fun requestPermission(): PermissionStatus
    fun showLocalNotification(title: String, message: String)
}

@Composable
expect fun rememberNotificationManager(): NotificationManager
