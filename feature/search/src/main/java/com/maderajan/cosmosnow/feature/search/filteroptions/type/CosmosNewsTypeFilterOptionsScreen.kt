package com.maderajan.cosmosnow.feature.search.filteroptions.type

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.RowCheckbox
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import com.maderajan.cosmosnow.data.model.comosnews.getPresentableNameRes
import com.maderajan.cosmosnow.feature.search.components.FilterContent

@Composable
fun CosmosNewsTypeFilterOptionsScreen(
    selectedTypes: List<CosmosNewsType>,
    dispatchAction: (CosmosNewsTypeFilterOptionsUiAction) -> Unit
) {
    FilterContent(
        title = stringResource(id = R.string.search_filter_type),
        onCancelClick = {
            dispatchAction(CosmosNewsTypeFilterOptionsUiAction.NavigateBack)
        },
        onCtaClick = {
            dispatchAction(CosmosNewsTypeFilterOptionsUiAction.ApplyFilter)
        },
        content = {
            CosmosNewsType.entries.map { type ->
                RowCheckbox(
                    text = stringResource(id = type.getPresentableNameRes()),
                    isChecked = selectedTypes.contains(type),
                    onCheckedChanged = { checked ->
                        dispatchAction(CosmosNewsTypeFilterOptionsUiAction.TypesChanged(checked, type))
                    }
                )
            }
        }
    )
}

@Preview
@Composable
fun CosmosNewsTypeFilterOptionsScreenPreview(isDarkTheme: Boolean = false) {
    CosmosNowTheme(isDarkTheme) {
        CosmosNewsTypeFilterOptionsScreen(
            selectedTypes = listOf(CosmosNewsType.ARTICLE),
            dispatchAction = {}
        )
    }
}