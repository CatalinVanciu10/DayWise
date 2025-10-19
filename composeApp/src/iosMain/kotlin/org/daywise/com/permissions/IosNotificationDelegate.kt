package org.daywise.com.permissions

import platform.UserNotifications.UNNotification
import platform.UserNotifications.UNNotificationPresentationOptionBanner
import platform.UserNotifications.UNNotificationPresentationOptionList
import platform.UserNotifications.UNNotificationPresentationOptionSound
import platform.UserNotifications.UNNotificationPresentationOptions
import platform.UserNotifications.UNNotificationResponse
import platform.UserNotifications.UNUserNotificationCenter
import platform.UserNotifications.UNUserNotificationCenterDelegateProtocol
import platform.darwin.NSObject

/**
 * Force showing notification in foreground
 */
class IosNotificationDelegate : NSObject(), UNUserNotificationCenterDelegateProtocol {

    override fun userNotificationCenter(
        center: UNUserNotificationCenter,
        willPresentNotification: UNNotification,
        withCompletionHandler: (UNNotificationPresentationOptions) -> Unit
    ) {
        println("💬 iOS delegate triggered for foreground notification")
        withCompletionHandler(
            UNNotificationPresentationOptionBanner or
                    UNNotificationPresentationOptionList or
                    UNNotificationPresentationOptionSound
        )
    }

    override fun userNotificationCenter(
        center: UNUserNotificationCenter,
        didReceiveNotificationResponse: UNNotificationResponse,
        withCompletionHandler: () -> Unit
    ) {
        val id = didReceiveNotificationResponse.notification.request.identifier()
        println("🔔 Notification tapped with id: $id")
        withCompletionHandler()
    }
}

fun setupIosNotificationsDelegate() {
    val center = UNUserNotificationCenter.currentNotificationCenter()
    println("💬 Delegate activat!")
    center.setDelegate(IosNotificationDelegate())
}
