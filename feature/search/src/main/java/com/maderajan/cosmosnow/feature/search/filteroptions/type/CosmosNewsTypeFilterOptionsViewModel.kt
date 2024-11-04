package com.maderajan.cosmosnow.feature.search.filteroptions.type

import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.core.navigation.Navigator
import com.maderajan.cosmosnow.core.viewmodel.BaseViewModel
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import com.maderajan.cosmosnow.domain.cosmosnews.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CosmosNewsTypeFilterOptionsViewModel @Inject constructor(
    private val navigator: Navigator,
    private val searchNewsUseCase: SearchNewsUseCase
) : BaseViewModel<CosmosNewsTypeFilterOptionsUiAction>() {

    val uiState = MutableStateFlow<List<CosmosNewsType>>(emptyList())

    override fun handleAction(action: CosmosNewsTypeFilterOptionsUiAction) {
        when (action) {
            is CosmosNewsTypeFilterOptionsUiAction.ProvideData -> {
                uiState.value = action.types
            }

            is CosmosNewsTypeFilterOptionsUiAction.TypesChanged -> {
                uiState.value = searchNewsUseCase.modifySelectedTypes(
                    isChecked = action.isChecked,
                    type = action.type,
                    currentTypes = uiState.value
                )
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