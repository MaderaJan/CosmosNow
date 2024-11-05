package com.maderajan.cosmosnow.domain.cosmosnews

import app.cash.turbine.test
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import com.maderajan.cosmosnow.data.model.comosnews.SearchDate
import com.maderajan.cosmosnow.data.repository.bookmarks.IBookmarkRepository
import com.maderajan.cosmosnow.domain.cosmosnews.fake.FakeBookmarkRepository
import com.maderajan.cosmosnow.domain.cosmosnews.fake.FakeCosmosNewsListRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CosmosNewsListUseCaseTest : BaseUnitTest() {

    private lateinit var sut: CosmosNewsListUseCase
    private lateinit var fakeNewsListRepository: FakeCosmosNewsListRepository
    private lateinit var fakeBookmarkRepository: IBookmarkRepository

    @Before
    override fun setup() {
        fakeNewsListRepository = FakeCosmosNewsListRepository()
        fakeBookmarkRepository = FakeBookmarkRepository()

        sut = CosmosNewsListUseCase(
            cosmosNewsRepository = fakeNewsListRepository,
            bookmarkRepository = fakeBookmarkRepository
        )
    }

    @Test
    fun areNews_Sorted_ByDescending() {
        testScope.runTest {
            setupFakeRepositoryWhichReturnEachOfType()

            fakeBookmarkRepository.saveBookmark(CosmosNews.fake(id = 1, publishedAt = "2025-07-05T22:34:26Z"))
            fakeBookmarkRepository.saveBookmark(CosmosNews.fake(id = 3, publishedAt = "2025-07-05T22:34:26Z"))

            sut.getSortedNewsFlow().test {
                val sortedNews = awaitItem()
                assert(
                    sortedNews[0].id == 3L && sortedNews[1].id == 2L && sortedNews[2].id == 1L &&
                            sortedNews[0].isBookmarked &&
                            sortedNews[2].isBookmarked
                )
                awaitComplete()
            }
        }
    }

    @Test
    fun getAllNewsFor_Query_HasLaunchIsNull_And_TypesAreEmpty() {
        setupQuerySearch(
            types = emptyList(),
            hasLaunch = null,
            assertion = { news ->
                news.size == 3
            }
        )
    }

    @Test
    fun getArticlesAndBlogsFor_Query_HasLaunchIsTrue_And_TypesAreEmpty() {
        setupQuerySearch(
            types = emptyList(),
            hasLaunch = true,
            assertion = { news ->
                news.size == 2 && news.none { it.type == CosmosNewsType.REPORT }
            }
        )
    }

    @Test
    fun getArticlesFor_Query_HasLaunchIsNull_And_TypesContainsArticle() {
        setupQuerySearch(
            types = listOf(CosmosNewsType.ARTICLE),
            hasLaunch = null,
            assertion = { news ->
                news.size == 1 && news.all { it.type == CosmosNewsType.ARTICLE }
            }
        )
    }

    private fun setupQuerySearch(
        types: List<CosmosNewsType>,
        hasLaunch: Boolean?,
        assertion: (List<CosmosNews>) -> Boolean
    ) {
        setupFakeRepositoryWhichReturnEachOfType()

        testScope.runTest {
            sut.getSortedNewsByQuery(
                types = types,
                hasLaunch = hasLaunch,
                searchText = "",
                newsSites = emptyList(),
                date = SearchDate.TODAY,
            ).test {
                val news = awaitItem()

                assert(assertion(news))
                awaitComplete()
            }
        }
    }

    private fun setupFakeRepositoryWhichReturnEachOfType() {
        fakeNewsListRepository.articlesFake = listOf(CosmosNews.fake(id = 1, publishedAt = "2023-07-05T22:34:26Z"))
        fakeNewsListRepository.blogFake = listOf(CosmosNews.fake(id = 2, publishedAt = "2024-07-05T22:34:26Z"))
        fakeNewsListRepository.reportsFake = listOf(CosmosNews.fake(id = 3, publishedAt = "2025-07-05T22:34:26Z"))
    }
}