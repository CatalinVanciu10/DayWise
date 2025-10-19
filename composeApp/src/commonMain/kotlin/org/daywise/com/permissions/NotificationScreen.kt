package org.daywise.com.permissions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun NotificationScreen(notificationManager: NotificationManager) {
    var permission by remember { mutableStateOf<PermissionStatus?>(null) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 1️⃣ Butonul care cere permisiunea (popup nativ)
            Button(onClick = {
                scope.launch {
                    permission = notificationManager.requestPermission()
                }
            }) {
                Text("Cere permisiunea de notificări")
            }

            Spacer(Modifier.height(20.dp))

            // 2️⃣ Butonul care trimite notificarea locală (nativă)
            Button(
                onClick = {
                    notificationManager.showLocalNotification(
                        "Salut 👋",
                        "Aceasta este o notificare locală!"
                    )
                },
                enabled = permission == PermissionStatus.GRANTED
            ) {
                Text("Trimite notificare locală")
            }

            Spacer(Modifier.height(20.dp))

            // 3️⃣ Textul care arată statusul permisiunii
            Text(
                when (permission) {
                    PermissionStatus.GRANTED -> "✅ Permisiune acordată"
                    PermissionStatus.DENIED -> "❌ Permisiune refuzată"
                    null -> "Apasă butonul pentru a cere permisiunea"
                }
            )
        }
    }
}
