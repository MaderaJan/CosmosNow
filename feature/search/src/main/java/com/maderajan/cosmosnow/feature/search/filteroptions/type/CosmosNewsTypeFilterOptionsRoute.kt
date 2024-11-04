package com.maderajan.cosmosnow.feature.search.filteroptions.type

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.RowCheckbox
import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.core.navigation.Navigator
import com.maderajan.cosmosnow.core.viewmodel.BaseViewModel
import com.maderajan.cosmosnow.core.viewmodel.UiAction
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import com.maderajan.cosmosnow.data.model.comosnews.getPresentableNameRes
import com.maderajan.cosmosnow.feature.search.components.FilterContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

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

@HiltViewModel
class CosmosNewsTypeFilterOptionsViewModel @Inject constructor(
    private val navigator: Navigator
) : BaseViewModel<CosmosNewsTypeFilterOptionsUiAction>() {

    val uiState = MutableStateFlow<List<CosmosNewsType>>(emptyList())

    override fun handleAction(action: CosmosNewsTypeFilterOptionsUiAction) {
        when (action) {
            is CosmosNewsTypeFilterOptionsUiAction.ProvideData -> {
                uiState.value = action.types
            }

            is CosmosNewsTypeFilterOptionsUiAction.TypesChanged -> {
                // TODO UNIT TEST
                if (action.isChecked) {
                    uiState.value += listOf(action.type)
                } else {
                    uiState.value = uiState.value.filter { it != action.type }
                }
            }

            CosmosNewsTypeFilterOptionsUiAction.ApplyFilter -> {
                navigator.navigateUpWithResult(key = CosmosScreens.SearchNewsFilterCosmosNewsType.RESULT_KEY, result = uiState.value)
            }

            CosmosNewsTypeFilterOptionsUiAction.NavigateBack -> {
                navigator.navigateUp()
            }
        }
    }
}

sealed interface CosmosNewsTypeFilterOptionsUiAction : UiAction {
    data class ProvideData(val types: List<CosmosNewsType>) : CosmosNewsTypeFilterOptionsUiAction
    data class TypesChanged(val isChecked: Boolean, val type: CosmosNewsType) : CosmosNewsTypeFilterOptionsUiAction
    data object NavigateBack : CosmosNewsTypeFilterOptionsUiAction
    data object ApplyFilter : CosmosNewsTypeFilterOptionsUiAction
}