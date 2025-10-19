package org.daywise.com.permissions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.datetime.Clock
import platform.UserNotifications.UNAuthorizationOptionAlert
import platform.UserNotifications.UNAuthorizationOptionBadge
import platform.UserNotifications.UNAuthorizationOptionSound
import platform.UserNotifications.UNMutableNotificationContent
import platform.UserNotifications.UNNotificationRequest
import platform.UserNotifications.UNNotificationSound
import platform.UserNotifications.UNTimeIntervalNotificationTrigger
import platform.UserNotifications.UNUserNotificationCenter
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.coroutines.resume
import kotlin.time.TimeSource

class IosNotificationManager : NotificationManager {


    override suspend fun requestPermission(): PermissionStatus =
        suspendCancellableCoroutine { continuation ->
            val center = UNUserNotificationCenter.currentNotificationCenter()
            center.requestAuthorizationWithOptions(
                options = UNAuthorizationOptionAlert or
                        UNAuthorizationOptionSound or
                        UNAuthorizationOptionBadge
            ) { granted, _ ->
                continuation.resume(
                    if (granted) PermissionStatus.GRANTED
                    else PermissionStatus.DENIED
                )
            }
        }


    override fun showLocalNotification(title: String, message: String) {
        dispatch_async(dispatch_get_main_queue()) {
            val content = UNMutableNotificationContent().apply {
                setTitle(title)
                setBody(message)
                setSound(UNNotificationSound.defaultSound())
            }

            val trigger = UNTimeIntervalNotificationTrigger.triggerWithTimeInterval(2.0, false)
            val id = "local_notification_${Clock.System.now().toEpochMilliseconds()}"

            val request = UNNotificationRequest.requestWithIdentifier(id, content, trigger)
            val center = UNUserNotificationCenter.currentNotificationCenter()

            center.addNotificationRequest(request) { error ->
                if (error != null) {
                    println("❌ Error: ${error.localizedDescription}")
                } else {
                    println("✅ Notification scheduled successfully")
                }
            }
        }
    }


}


@Composable
actual fun rememberNotificationManager(): NotificationManager {
    return remember { IosNotificationManager() }
}
