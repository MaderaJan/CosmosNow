package com.maderajan.cosmosnow.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.core.designsystem.theme.spacing

@Composable
fun DropDownChip(
    text: String,
    isActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val onChipColor = if (isActive) Color.White else MaterialTheme.colorScheme.onSurface

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable(onClick = onClick)
            .background(
                color = if (isActive) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.surfaceContainerHighest
                }, shape = CircleShape
            )
            .padding(
                top = MaterialTheme.spacing.extraSmall,
                bottom = MaterialTheme.spacing.extraSmall,
                end = MaterialTheme.spacing.small,
                start = MaterialTheme.spacing.medium,
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = onChipColor
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_drop_down),
            contentDescription = null,
            tint = onChipColor
        )
    }
}

@Preview
@Composable
fun DropDownChipInActivePreview() {
    CosmosNowTheme {
        DropDownChip(
            text = "InActive Chip",
            isActive = false,
            onClick = {}
        )
    }
}

@Preview
@Composable
fun DropDownChipActivePreview() {
    CosmosNowTheme {
        DropDownChip(
            text = "Active Chip",
            isActive = true,
            onClick = {}
        )
    }
}