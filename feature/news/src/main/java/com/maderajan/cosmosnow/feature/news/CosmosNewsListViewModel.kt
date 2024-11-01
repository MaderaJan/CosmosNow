package com.maderajan.cosmosnow.feature.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.domain.cosmosnews.CosmosNewsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CosmosNewsListViewModel @Inject constructor(
    cosmosNewsListUseCase: CosmosNewsListUseCase
) : ViewModel() {

    val news: StateFlow<List<CosmosNews>> =
        cosmosNewsListUseCase.getNews()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = emptyList(),
            )
}