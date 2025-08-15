package org.daywise.com.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BouncingDotsLoading(
    modifier: Modifier = Modifier,
    dotCount: Int = 3,
    dotSize: Dp = 8.dp,
    bounceHeight: Dp = 6.dp,
    color: Color = MaterialTheme.colorScheme.onSecondary
) {
    val animatables = List(dotCount) { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            animatables.forEachIndexed { index, anim ->
                launch {
                    anim.animateTo(
                        targetValue = -bounceHeight.value,
                        animationSpec = tween(durationMillis = 300, easing = LinearEasing)
                    )
                    anim.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(durationMillis = 300, easing = LinearEasing)
                    )
                }
                delay(150)
            }
        }
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        animatables.forEach { anim ->
            Box(
                modifier = Modifier
                    .size(dotSize)
                    .offset(y = anim.value.dp)
                    .background(color, shape = CircleShape)
            )
        }
    }
}
