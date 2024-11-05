package com.maderajan.cosmosnow.feature.search.filteroptions.sites

data class NewsSitesFilterOptionsUiState(
    val selectedSites: List<String> = emptyList(),
    val allSites: List<String> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)