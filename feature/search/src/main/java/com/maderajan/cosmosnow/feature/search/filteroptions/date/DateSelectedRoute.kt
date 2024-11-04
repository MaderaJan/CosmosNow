package com.maderajan.cosmosnow.feature.search.filteroptions.date

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.RowRadioButton
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.data.model.comosnews.SearchDate
import com.maderajan.cosmosnow.feature.search.components.FilterContent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelectedRoute(
    date: SearchDate?,
    viewModel: DateSelectedViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.dispatch(DateSelectedUiAction.ProvideData(date = date))
    }

    ModalBottomSheet(
        onDismissRequest = {
            viewModel.dispatch(DateSelectedUiAction.NavigateBack)
        },
        content = {
            DateSelectScreen(
                date = viewModel.uiState.collectAsState().value,
                dispatchAction = viewModel::dispatch
            )
        }
    )
}

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
        onCtaClick = {
            dispatchAction(DateSelectedUiAction.ApplyFilter)
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
fun DateSelectScreenPreview() {
    CosmosNowTheme {
        DateSelectScreen(
            date = SearchDate.LAST_WEEK,
            dispatchAction = {}
        )
    }
}