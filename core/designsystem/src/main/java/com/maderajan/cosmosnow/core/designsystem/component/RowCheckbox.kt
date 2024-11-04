package com.maderajan.cosmosnow.core.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.core.designsystem.theme.spacing

@Composable
fun RowCheckbox(
    text: String,
    isChecked: Boolean,
    onCheckedChanged: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = isChecked,
                onClick = { onCheckedChanged(!isChecked) }
            )
            .padding(horizontal = MaterialTheme.spacing.medium)
    ) {

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
        )


        Spacer(modifier = Modifier.weight(1f))

        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChanged
        )
    }
}

@Preview
@Composable
fun RowCheckboxPreview() {
    CosmosNowTheme {
        Column {
            RowCheckbox(
                text = "Unselected checkbox",
                isChecked = false,
                onCheckedChanged = {}
            )

            RowCheckbox(
                text = "Selected checkbox",
                isChecked = true,
                onCheckedChanged = {}
            )
        }
    }
}