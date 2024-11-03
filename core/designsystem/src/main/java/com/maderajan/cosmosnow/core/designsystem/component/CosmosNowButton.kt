package com.maderajan.cosmosnow.core.designsystem.component

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme

@Composable
fun CosmosNowButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        content = {
            Text(
                text = text,
                color = Color.White
            )
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun CosmosNowButtonPreview() {
    CosmosNowTheme {
        CosmosNowButton(
            text = "Button Text",
            onClick = {}
        )
    }
}