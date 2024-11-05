package com.maderajan.cosmosnow.feature.newsdetail.changefont

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.BottomSheetHeader
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.core.designsystem.theme.spacing

@Composable
fun ChangeNewsFontScreen(
    fontSize: Int,
    dispatchAction: (ChangeNewsFontUiAction) -> Unit
) {
    Column {
        BottomSheetHeader(
            title = stringResource(id = R.string.increase_font_size_title),
            onCancelClick = {
                dispatchAction(ChangeNewsFontUiAction.NavigateBack)
            }
        )

        Text(
            text = stringResource(id = R.string.increase_font_size_description),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = fontSize.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
                .height(150.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.mediumLarge,
                )
        ) {
            BigIconButton(
                icon = painterResource(id = R.drawable.ic_remove),
                onClick = {
                    dispatchAction(ChangeNewsFontUiAction.DecreaseFont)
                }
            )

            Text(
                text = fontSize.toString(),
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
            )

            BigIconButton(
                icon = painterResource(id = R.drawable.ic_add),
                onClick = {
                    dispatchAction(ChangeNewsFontUiAction.ChangeNewsFont)
                }
            )
        }
    }
}

@Composable
private fun BigIconButton(
    icon: Painter,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        content = {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = Color.White
            )
        },
        modifier = Modifier
            .size(45.dp)
            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(10.dp))
    )
}

@Preview
@Composable
fun ChangeNewsFontScreenPreview(isDarkTheme: Boolean = false) {
    CosmosNowTheme(isDarkTheme) {
        ChangeNewsFontScreen(
            fontSize = 16,
            dispatchAction = { }
        )
    }
}