package com.maderajan.cosmosnow.feature.search.filteroptions.sites

import com.maderajan.cosmosnow.core.viewmodel.UiState

data class NewsSitesFilterOptionsUiState(
    val selectedSites: List<String> = emptyList(),
    val allSites: List<String> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
): UiState