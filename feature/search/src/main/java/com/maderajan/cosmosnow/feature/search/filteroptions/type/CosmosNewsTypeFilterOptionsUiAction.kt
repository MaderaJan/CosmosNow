package com.maderajan.cosmosnow.feature.search.filteroptions.type

import com.maderajan.cosmosnow.core.viewmodel.UiAction
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType

sealed interface CosmosNewsTypeFilterOptionsUiAction : UiAction {
    data class ProvideData(val types: List<CosmosNewsType>) : CosmosNewsTypeFilterOptionsUiAction
    data class TypesChanged(val isChecked: Boolean, val type: CosmosNewsType) : CosmosNewsTypeFilterOptionsUiAction
    data object NavigateBack : CosmosNewsTypeFilterOptionsUiAction
    data object ApplyFilter : CosmosNewsTypeFilterOptionsUiAction
}