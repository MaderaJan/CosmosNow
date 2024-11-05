package com.maderajan.cosmosnow.feature.search.filteroptions.launch

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.RowRadioButton
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.feature.search.components.FilterContent

@Composable
fun LaunchFilterOptionsScreen(
    launchBoolean: Boolean?,
    dispatchAction: (LaunchOptionsUiAction) -> Unit
) {
    Column {
        FilterContent(
            title = stringResource(id = R.string.search_filter_launch),
            onCancelClick = {
                dispatchAction(LaunchOptionsUiAction.NavigateBack)
            },
            onCtaClick = {
                dispatchAction(LaunchOptionsUiAction.ApplyFilter)
            },
            content = {
                RowRadioButton(
                    text = stringResource(id = R.string.search_filter_none),
                    isSelected = launchBoolean == null,
                    onClick = {
                        dispatchAction(LaunchOptionsUiAction.NoneSelected)
                    }
                )

                RowRadioButton(
                    text = stringResource(id = R.string.search_filter_launched),
                    isSelected = launchBoolean == true,
                    onClick = {
                        dispatchAction(LaunchOptionsUiAction.LaunchedSelected)
                    }
                )

                RowRadioButton(
                    text = stringResource(id = R.string.search_filter_not_launched),
                    isSelected = launchBoolean == false,
                    onClick = {
                        dispatchAction(LaunchOptionsUiAction.NotLaunchedSelected)
                    }
                )
            }
        )
    }
}

@Composable
@Preview
fun LaunchFilterOptionsScreenPreview(isDarkTheme: Boolean = false) {
    CosmosNowTheme(isDarkTheme) {
        LaunchFilterOptionsScreen(
            launchBoolean = true,
            dispatchAction = {}
        )
    }
}