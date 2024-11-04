package com.maderajan.cosmosnow.domain.cosmosnews

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import com.maderajan.cosmosnow.data.model.comosnews.SearchDate
import com.maderajan.cosmosnow.data.model.comosnews.SearchQuery
import com.maderajan.cosmosnow.data.repository.bookmarks.IBookmarkRepository
import com.maderajan.cosmosnow.data.repository.cosmosnews.ICosmosNewsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
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

    fun getSearchSortedNews(
        searchText: String,
        newsSites: List<String>,
        types: List<CosmosNewsType>,
        date: SearchDate?,
        hasLaunch: Boolean?
    ): Flow<List<CosmosNews>> {
        val (dateFrom, dateTo) = getDateRange(date)

        val query = SearchQuery(
            searchText = searchText,
            newsSites = newsSites.joinToString(","),
            dateFrom = dateFrom,
            dateTo = dateTo,
            hasLaunch = hasLaunch
        )

        return flow {
            coroutineScope {
                val deferred = when {
                    query.hasLaunch == null || types.isEmpty() -> {
                        listOf(
                            async { cosmosNewsRepository.getArticles(query) },
                            async { cosmosNewsRepository.getBlogs(query) },
                            async { cosmosNewsRepository.getReports(query) },
                        )
                    }

                    hasLaunch == false -> {
                        val articleDeferred = if (types.contains(CosmosNewsType.ARTICLE)) {
                            async { cosmosNewsRepository.getArticles(query) }
                        } else {
                            null
                        }

                        val blogDeferred = if (types.contains(CosmosNewsType.BLOG)) {
                            async { cosmosNewsRepository.getBlogs(query) }
                        } else {
                            null
                        }

                        val reportDeferred = if (types.contains(CosmosNewsType.REPORT)) {
                            async { cosmosNewsRepository.getReports(query) }
                        } else {
                            null
                        }

                        listOfNotNull(articleDeferred, blogDeferred, reportDeferred)
                    }

                    else -> {
                        val articleDeferred = if (types.contains(CosmosNewsType.ARTICLE)) {
                            async { cosmosNewsRepository.getArticles(query) }
                        } else {
                            null
                        }

                        val blogDeferred = if (types.contains(CosmosNewsType.BLOG)) {
                            async { cosmosNewsRepository.getBlogs(query) }
                        } else {
                            null
                        }

                        listOfNotNull(articleDeferred, blogDeferred)
                    }
                }

                emit(deferred.awaitAll().flatten())
            }
        }
    }

    private fun getDateRange(date: SearchDate?): Pair<String?, String?> =
        when (date) {
            SearchDate.TODAY -> {
                val from = Calendar.getInstance().startOfDay()
                from.timeInMillis.toApiTimeFormat() to Calendar.getInstance().timeInMillis.toApiTimeFormat()
            }

            SearchDate.LAST_WEEK -> {
                val from = Calendar.getInstance().startOfDay()
                from.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
                from.add(Calendar.DAY_OF_MONTH, -7)

                val to: Calendar = (from.clone() as Calendar).endOfDay()
                to.add(Calendar.DAY_OF_MONTH, 6)

                from.timeInMillis.toApiTimeFormat() to to.timeInMillis.toApiTimeFormat()
            }

            SearchDate.LAST_MOTH -> {
                val from = Calendar.getInstance().startOfDay()
                from.add(Calendar.DAY_OF_MONTH, -30)
                from.set(Calendar.DAY_OF_MONTH, 1)

                val to: Calendar = (from.clone() as Calendar).endOfDay()
                to.set(Calendar.DAY_OF_MONTH, to.getActualMaximum(Calendar.DAY_OF_MONTH))

                from.timeInMillis.toApiTimeFormat() to to.timeInMillis.toApiTimeFormat()
            }

            else -> null to null
        }
}

fun Long.toApiTimeFormat(): String {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    return format.format(this)
}

fun Calendar.startOfDay(): Calendar =
    this.apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

fun Calendar.endOfDay(): Calendar =
    this.apply {
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
        set(Calendar.SECOND, 59)
        set(Calendar.MILLISECOND, 999)
    }