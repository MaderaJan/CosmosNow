package com.maderajan.cosmosnow.domain.cosmosnews

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.repository.bookmarks.IBookmarkRepository
import com.maderajan.cosmosnow.data.repository.cosmosnews.ICosmosNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CosmosNewsListUseCase @Inject constructor(
    private val cosmosNewsRepository: ICosmosNewsRepository,
    private val bookmarkRepository: IBookmarkRepository
) {

    fun getSortedNewsFlow(): Flow<List<CosmosNews>> =
        combine(
            bookmarkRepository.getAllBookmarksFlow(),
            flow {
                emit(cosmosNewsRepository.getArticles() + cosmosNewsRepository.getReports() + cosmosNewsRepository.getBlogs())
            }
        ) { bookmarkedNews, news ->
            news.map { newsToMap ->
                newsToMap.copy(isBookmarked = bookmarkedNews.any { it.id == newsToMap.id })
            }.sortedByDescending(CosmosNews::publishedAt)
        }
}