package com.maderajan.cosmosnow.feature.search.filteroptions.sites

import com.maderajan.cosmosnow.core.viewmodel.UiAction

sealed interface NewsSitesFilterOptionsUiAction : UiAction {
    data class ProvideData(val newsSites: List<String>) : NewsSitesFilterOptionsUiAction
    data class SiteChecked(val isChecked: Boolean, val site: String) : NewsSitesFilterOptionsUiAction
    data object NavigateBack : NewsSitesFilterOptionsUiAction
    data object ApplyFilter : NewsSitesFilterOptionsUiAction
}