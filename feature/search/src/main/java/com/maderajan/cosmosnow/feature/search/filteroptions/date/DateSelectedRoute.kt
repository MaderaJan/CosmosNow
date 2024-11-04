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
import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.core.navigation.Navigator
import com.maderajan.cosmosnow.core.viewmodel.BaseViewModel
import com.maderajan.cosmosnow.core.viewmodel.UiAction
import com.maderajan.cosmosnow.data.model.comosnews.DateSelect
import com.maderajan.cosmosnow.feature.search.components.FilterContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelectedRoute(
    date: DateSelect?,
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
    date: DateSelect?,
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
            DateSelect.entries.map { dateType ->
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
            date = DateSelect.LAST_WEEK,
            dispatchAction = {}
        )
    }
}

@HiltViewModel
class DateSelectedViewModel @Inject constructor(
    private val navigator: Navigator,
) : BaseViewModel<DateSelectedUiAction>() {

    val uiState = MutableStateFlow<DateSelect?>(null)

    override fun handleAction(action: DateSelectedUiAction) {
        when (action) {
            is DateSelectedUiAction.ProvideData -> {
                uiState.value = action.date
            }

            is DateSelectedUiAction.DateSelected -> {
                uiState.value = action.date
            }

            DateSelectedUiAction.ApplyFilter -> {
                navigator.navigateUpWithResult(CosmosScreens.SearchNewsFilterDate.RESULT_KEY, uiState.value)
            }

            DateSelectedUiAction.NavigateBack -> {
                navigator.navigateUp()
            }
        }
    }
}

sealed interface DateSelectedUiAction : UiAction {
    data class ProvideData(val date: DateSelect?) : DateSelectedUiAction
    data class DateSelected(val date: DateSelect) : DateSelectedUiAction
    data object NavigateBack : DateSelectedUiAction
    data object ApplyFilter : DateSelectedUiAction
}