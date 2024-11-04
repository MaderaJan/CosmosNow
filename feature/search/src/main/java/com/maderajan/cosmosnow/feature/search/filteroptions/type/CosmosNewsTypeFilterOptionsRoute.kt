package com.maderajan.cosmosnow.feature.search.filteroptions.type

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CosmosNewsTypeFilterOptionsRoute(
    types: List<CosmosNewsType>,
    viewModel: CosmosNewsTypeFilterOptionsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.dispatch(CosmosNewsTypeFilterOptionsUiAction.ProvideData(types))
    }

    ModalBottomSheet(
        onDismissRequest = {
            viewModel.dispatch(CosmosNewsTypeFilterOptionsUiAction.NavigateBack)
        },
        content = {
            CosmosNewsTypeFilterOptionsScreen(
                selectedTypes = viewModel.uiState.collectAsState().value,
                dispatchAction = viewModel::dispatch
            )
        }
    )
}