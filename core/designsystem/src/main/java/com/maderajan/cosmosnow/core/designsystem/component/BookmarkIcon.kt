package com.maderajan.cosmosnow.core.designsystem.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.maderajan.cosmosnow.core.designsystem.R

@Composable
fun BookmarkIcon(
    isBookmarked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        content = {
            Icon(
                painter = painterResource(
                    id = if (isBookmarked) {
                        R.drawable.ic_bookmark_filled
                    } else {
                        R.drawable.ic_bookmark
                    }
                ),
                contentDescription = null
            )
        },
        onClick = onClick,
        modifier = modifier
    )
}