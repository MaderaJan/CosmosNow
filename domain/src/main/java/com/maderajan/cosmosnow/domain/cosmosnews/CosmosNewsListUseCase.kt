package com.maderajan.cosmosnow.domain.cosmosnews

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import com.maderajan.cosmosnow.data.model.comosnews.SearchDate
import com.maderajan.cosmosnow.data.model.comosnews.SearchQuery
import com.maderajan.cosmosnow.data.model.comosnews.getFromToDateString
import com.maderajan.cosmosnow.data.repository.bookmarks.IBookmarkRepository
import com.maderajan.cosmosnow.data.repository.cosmosnews.ICosmosNewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
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
                val news = coroutineScope {
                    getAllNews(query = null).awaitAll().flatten()
                }
                emit(news)
            }, sortAndMapWithBookMarks()
        )

    private fun sortAndMapWithBookMarks(): suspend (a: List<CosmosNews>, b: List<CosmosNews>) -> List<CosmosNews> =
        { bookmarkedNews, news ->
            news.map { newsToMap ->
                newsToMap.copy(isBookmarked = bookmarkedNews.any { it.id == newsToMap.id })
            }.sortedByDescending(CosmosNews::publishedAt)
        }

    fun getSearchSortedNews(
        searchText: String,
        newsSites: List<String>,
        types: List<CosmosNewsType>,
        date: SearchDate?,
        hasLaunch: Boolean?
    ): Flow<List<CosmosNews>> {
        val (dateFrom, dateTo) = date.getFromToDateString()

        val query = SearchQuery(
            searchText = searchText,
            newsSites = newsSites.joinToString(","),
            dateFrom = dateFrom,
            dateTo = dateTo,
            hasLaunch = hasLaunch
        )

        return combine(
            bookmarkRepository.getAllBookmarksFlow(),
            flow {
                coroutineScope {
                    val news = getCosmosNewsFlowByQuery(query, types).awaitAll().flatten()
                    emit(news)
                }
            }, sortAndMapWithBookMarks()
        )
    }

    private fun CoroutineScope.getCosmosNewsFlowByQuery(query: SearchQuery, types: List<CosmosNewsType>): List<Deferred<List<CosmosNews>>> =
        when {
            query.hasLaunch == null || types.isEmpty() -> {
                getAllNews(query)
            }

            query.hasLaunch == false -> {
                listOfNotNull(getArticlesDeferred(types, query), getBlogDeferred(types, query), getReportsDeferred(types, query))
            }

            else -> {
                listOfNotNull(getArticlesDeferred(types, query), getBlogDeferred(types, query))
            }
        }

    private fun CoroutineScope.getAllNews(query: SearchQuery?): List<Deferred<List<CosmosNews>>> = listOf(
        async { cosmosNewsRepository.getArticles(query) },
        async { cosmosNewsRepository.getBlogs(query) },
        async { cosmosNewsRepository.getReports(query) },
    )

    private fun CoroutineScope.getArticlesDeferred(types: List<CosmosNewsType>, query: SearchQuery): Deferred<List<CosmosNews>>? =
        if (types.contains(CosmosNewsType.ARTICLE)) {
            async { cosmosNewsRepository.getArticles(query) }
        } else {
            null
        }

    private fun CoroutineScope.getBlogDeferred(types: List<CosmosNewsType>, query: SearchQuery) =
        if (types.contains(CosmosNewsType.BLOG)) {
            async { cosmosNewsRepository.getBlogs(query) }
        } else {
            null
        }

    private fun CoroutineScope.getReportsDeferred(types: List<CosmosNewsType>, query: SearchQuery): Deferred<List<CosmosNews>>? =
        if (types.contains(CosmosNewsType.REPORT)) {
            async { cosmosNewsRepository.getReports(query) }
        } else {
            null
        }
}