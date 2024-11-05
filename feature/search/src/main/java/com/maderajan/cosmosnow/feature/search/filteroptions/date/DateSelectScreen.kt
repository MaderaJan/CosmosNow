package com.maderajan.cosmosnow.feature.search.filteroptions.date

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.RowRadioButton
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.data.model.comosnews.SearchDate
import com.maderajan.cosmosnow.feature.search.components.FilterContent

@Composable
fun DateSelectScreen(
    date: SearchDate?,
    dispatchAction: (DateSelectedUiAction) -> Unit
) {
    FilterContent(
        title = stringResource(id = R.string.search_filter_date),
        onCancelClick = {
            dispatchAction(DateSelectedUiAction.NavigateBack)
        },
        onApplyClick = {
            dispatchAction(DateSelectedUiAction.ApplyFilter)
        },
        onClearClick = {
            dispatchAction(DateSelectedUiAction.ClearFilter)
        },
        content = {
            SearchDate.entries.map { dateType ->
                RowRadioButton(
                    text = stringResource(id = dateType.getPresentableName()),
                    isSelected = date == dateType,
                    onClick = {
                        dispatchAction(DateSelectedUiAction.DateSelected(dateType))
                    }
                )
            }
        }
    )
}

@Preview
@Composable
fun DateSelectScreenPreview(isDarkTheme: Boolean = false) {
    CosmosNowTheme(isDarkTheme) {
        DateSelectScreen(
            date = SearchDate.LAST_WEEK,
            dispatchAction = {}
        )
    }
}