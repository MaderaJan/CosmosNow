package com.maderajan.cosmosnow.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme

@Composable
fun CosmosNowButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: CosmosNowButtonData = CosmosNowButtonDefaults.contained(),
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.backgroundColor
        ),
        border = BorderStroke(1.dp, colors.strokeColor),
        content = {
            Text(
                text = text,
                color = colors.textColor
            )
        },
        modifier = modifier
    )
}

data class CosmosNowButtonData(
    val textColor: Color,
    val strokeColor: Color,
    val backgroundColor: Color,
)

object CosmosNowButtonDefaults {

    @Composable
    fun contained() =
        CosmosNowButtonData(
            textColor = Color.White,
            backgroundColor = MaterialTheme.colorScheme.primary,
            strokeColor = MaterialTheme.colorScheme.primary,
        )

    @Composable
    fun text() =
        CosmosNowButtonData(
            textColor = MaterialTheme.colorScheme.primary,
            backgroundColor = Color.Transparent,
            strokeColor = Color.Transparent,
        )

    @Composable
    fun outline() =
        CosmosNowButtonData(
            textColor = MaterialTheme.colorScheme.primary,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
            strokeColor = MaterialTheme.colorScheme.primary,
        )
}

@Preview
@Composable
fun CosmosNowButtonPreview() {
    CosmosNowTheme {
        Column {
            CosmosNowButton(
                text = "Button Text",
                onClick = {},
                colors = CosmosNowButtonDefaults.contained()
            )

            CosmosNowButton(
                text = "Button Text",
                onClick = {},
                colors = CosmosNowButtonDefaults.outline()
            )

            CosmosNowButton(
                text = "Button Text",
                onClick = {},
                colors = CosmosNowButtonDefaults.text()
            )
        }
    }
}