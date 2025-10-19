package org.daywise.com

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import org.daywise.com.permissions.AndroidNotificationManager

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val calendarLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->

            }

        val notificationLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                if(!granted) {
                    println("❌ Notification permission denied")
                    calendarLauncher.launch(Manifest.permission.READ_CALENDAR)
                } else {
                    println("✅ Notification permission granted")
                    calendarLauncher.launch(Manifest.permission.READ_CALENDAR)
                }
            }

        val notificationManager = AndroidNotificationManager(this, notificationLauncher)


        setContent {
            LaunchedEffect(Unit) {
                notificationManager.requestPermission()
            }
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
