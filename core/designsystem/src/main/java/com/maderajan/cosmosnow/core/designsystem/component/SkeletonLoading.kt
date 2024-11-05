package com.maderajan.cosmosnow.core.designsystem.component

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.maderajan.cosmosnow.core.designsystem.theme.spacing

class ShimmerBrushBuilder {

    private val composable = mutableListOf<@Composable () -> Unit>()

    fun add(item: ShimmerItem): ShimmerBrushBuilder {
        when (item) {
            is ShimmerItem.Space -> {
                composable.add { Spacer(modifier = Modifier.heightIn(item.height)) }
            }

            is ShimmerItem.Box -> {
                composable.add { ShimmerBox(item.height, widthFraction = 1f) }
            }

            is ShimmerItem.Text -> {
                repeat(item.numberOfLine) {
                    composable.add { ShimmerBox(height = 16.dp, widthFraction = if (it % 2 == 0) 1f else 0.75f) }
                    composable.add { Spacer(modifier = Modifier.heightIn(MaterialTheme.spacing.extraSmall)) }
                }
            }

            ShimmerItem.BoxText -> {
                composable.add {
                    Row {
                        ShimmerBox(100.dp)

                        Spacer(modifier = Modifier.heightIn(MaterialTheme.spacing.medium))

                        Column(
                            modifier = Modifier.padding(start = MaterialTheme.spacing.medium)
                        ) {
                            Spacer(modifier = Modifier.heightIn(MaterialTheme.spacing.medium))

                            repeat(2) {
                                ShimmerBox(height = 16.dp, widthFraction = if (it % 2 == 0) 1f else 0.75f)
                                Spacer(modifier = Modifier.heightIn(MaterialTheme.spacing.extraSmall))
                            }
                        }
                    }
                }
            }
        }

        return this
    }


    @Composable
    private fun ShimmerBox(height: Dp, widthFraction: Float) {
        Box(
            modifier = Modifier
                .fillMaxWidth(widthFraction)
                .background(shimmerBrush(showShimmer = true), shape = RoundedCornerShape(MaterialTheme.spacing.medium))
                .height(height),
        )
    }

    @Composable
    private fun ShimmerBox(size: Dp) {
        Box(
            modifier = Modifier
                .background(shimmerBrush(showShimmer = true), shape = RoundedCornerShape(MaterialTheme.spacing.medium))
                .size(size),
        )
    }

    @Composable
    fun Compose(modifier: Modifier = Modifier) {
        Column(
            modifier = modifier,
        ) {
            composable.forEach { it() }
        }
    }
}

sealed interface ShimmerItem {
    data class Box(val height: Dp) : ShimmerItem
    data class Space(val height: Dp) : ShimmerItem
    data object BoxText : ShimmerItem
    data class Text(val numberOfLine: Int) : ShimmerItem
}

@Composable
fun shimmerBrush(showShimmer: Boolean = true, targetValue: Float = 1000f): Brush {
    return if (showShimmer) {
        val shimmerColors = listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.6f),
        )

        val transition = rememberInfiniteTransition(label = "")
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 800),
                repeatMode = RepeatMode.Reverse,
            ),
            label = "",
        )
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value),
        )
    } else {
        Brush.linearGradient(
            colors = listOf(Color.Transparent, Color.Transparent),
            start = Offset.Zero,
            end = Offset.Zero,
        )
    }
}
