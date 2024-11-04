package com.maderajan.cosmosnow.feature.search.filteroptions.date

import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.core.navigation.Navigator
import com.maderajan.cosmosnow.core.viewmodel.BaseViewModel
import com.maderajan.cosmosnow.data.model.comosnews.DateSelect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

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