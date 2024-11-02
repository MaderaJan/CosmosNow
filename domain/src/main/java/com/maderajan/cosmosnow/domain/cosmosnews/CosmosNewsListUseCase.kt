package com.maderajan.cosmosnow.domain.cosmosnews

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.repository.cosmosnews.ICosmosNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CosmosNewsListUseCase @Inject constructor(
    private val cosmosNewsRepository: ICosmosNewsRepository
) {

    fun getSortedNews(offset: Int): Flow<List<CosmosNews>> = flow {
        val news = cosmosNewsRepository
            .getArticles(offset)
            .sortedBy(CosmosNews::publishedAt)

        emit(news)
    }
}