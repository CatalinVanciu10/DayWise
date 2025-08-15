package org.daywise.com

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightBlue = Color(0xFF4A90E2)
val SoftYellow = Color(0xFFFFD166)
val OffWhite = Color(0xFFF9F9F9)
val DarkGray = Color(0xFF333333)

private val LightColors = lightColorScheme(
    primary = LightBlue,
    onPrimary = Color.White,
    secondary = SoftYellow,
    onSecondary = DarkGray,
    background = OffWhite,
    onBackground = DarkGray,
    surface = Color.White,
    onSurface = DarkGray
)

private val DarkColors = darkColorScheme(
    primary = LightBlue,
    onPrimary = Color.White,
    secondary = SoftYellow,
    onSecondary = DarkGray,
    background = Color(0xFF121212),
    onBackground = Color.White,
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White
)

@Composable
fun DayWiseTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = androidx.compose.material3.Typography(),
        content = content
    )
}
