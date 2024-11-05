package com.maderajan.cosmosnow.feature.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.BottomSheetHeader
import com.maderajan.cosmosnow.core.designsystem.component.CosmosNowButton
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.core.designsystem.theme.spacing

@Composable
fun FilterContent(
    title: String,
    onCancelClick: () -> Unit,
    onCtaClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    hideApplyFilter: Boolean = false,
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (contentRef, ctaRef) = createRefs()

        Column(
            modifier = Modifier.constrainAs(contentRef) {
                top.linkTo(parent.top)
                if (hideApplyFilter) {
                    bottom.linkTo(parent.bottom)
                } else {
                    bottom.linkTo(ctaRef.top)
                }
            }
        ) {
            BottomSheetHeader(
                title = title,
                onCancelClick = onCancelClick,
                modifier = Modifier
                    .padding(bottom = MaterialTheme.spacing.small)
            )

            content()
        }

        if (!hideApplyFilter) {
            CosmosNowButton(
                text = stringResource(id = R.string.search_filter_apply),
                onClick = onCtaClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium)
                    .constrainAs(ctaRef) {
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }

}

@Preview
@Composable
fun FilterContentPreview() {
    CosmosNowTheme {
        FilterContent(
            title = "Title",
            onCancelClick = {},
            onCtaClick = {},
            content = {
                Text(text = "Content")
            }
        )
    }
}