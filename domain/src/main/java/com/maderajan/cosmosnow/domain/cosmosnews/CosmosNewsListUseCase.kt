package com.maderajan.cosmosnow.domain.cosmosnews

import com.maderajan.cosmosnow.data.repository.cosmosnews.ICosmosNewsRepository
import javax.inject.Inject

class CosmosNewsListUseCase @Inject constructor(
    private val cosmosNewsRepository: ICosmosNewsRepository
) {

    fun getNews() {
        // TODO continue impl
    }
}