package com.maderajan.cosmosnow.feature.search.filteroptions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.BottomSheetHeader
import com.maderajan.cosmosnow.core.designsystem.component.CosmosNowButton
import com.maderajan.cosmosnow.core.designsystem.component.RowRadioButton
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.core.designsystem.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchFilterOptionBottomSheetRoute(
    hasLaunch: Boolean?,
    viewModel: LaunchOptionsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.dispatch(LaunchOptionsUiAction.ProvideData(hasLaunch = hasLaunch))
    }

    ModalBottomSheet(
        onDismissRequest = {
            viewModel.dispatch(LaunchOptionsUiAction.NavigateBack)
        },
        content = {
            LaunchFilterOptionsScreen(
                launchBoolean = viewModel.uiState.collectAsState().value,
                dispatchAction = viewModel::dispatch
            )
        }
    )
}

@Composable
fun LaunchFilterOptionsScreen(
    launchBoolean: Boolean?,
    dispatchAction: (LaunchOptionsUiAction) -> Unit
) {
    Column {
        BottomSheetHeader(
            title = stringResource(id = R.string.search_filter_launch),
            onCancelClick = {
                dispatchAction(LaunchOptionsUiAction.NavigateBack)
            },
            modifier = Modifier.padding(bottom = MaterialTheme.spacing.small)
        )

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

        CosmosNowButton(
            text = stringResource(id = R.string.search_filter_apply),
            onClick = {
                dispatchAction(LaunchOptionsUiAction.ApplyFilter)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium)
        )
    }
}

@Composable
@Preview
fun LaunchFilterOptionsScreenPreview() {
    CosmosNowTheme {
        LaunchFilterOptionsScreen(
            launchBoolean = true,
            dispatchAction = {}
        )
    }
}