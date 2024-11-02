package com.maderajan.cosmosnow.core.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.core.designsystem.theme.spacing

@Composable
fun NoContent(
    noContentData: NoContentData,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_no_internet),
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            text = noContentData.title,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        if (noContentData.description != null) {
            Text(
                text = noContentData.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        if (noContentData.buttonText != null && noContentData.onButtonClick != null) {
            Button(
                onClick = noContentData.onButtonClick,
                content = {
                    Text(
                        text = noContentData.buttonText,
                        color = Color.White
                    )
                },
                modifier = Modifier.padding(top = MaterialTheme.spacing.small)
            )
        }
    }
}

data class NoContentData(
    val title: String,
    val modifier: Modifier = Modifier,
    val description: String? = null,
    val buttonText: String? = null,
    val onButtonClick: (() -> Unit)? = null,
)

data object NoContentDefaults {

    @Composable
    fun default(onButtonClick: (() -> Unit)): NoContentData =
        NoContentData(
            title = stringResource(id = R.string.no_content_network_error_title),
            description = stringResource(id = R.string.no_content_network_error_description),
            buttonText = stringResource(id = R.string.no_content_network_error_button),
            onButtonClick = onButtonClick
        )
}

@Preview
@Composable
fun NoContentPreview() {
    CosmosNowTheme {
        NoContent(noContentData = NoContentDefaults.default(onButtonClick = {}))
    }
}