package com.maderajan.cosmosnow.feature.search.filteroptions.date

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.maderajan.cosmosnow.data.model.comosnews.SearchDate


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

