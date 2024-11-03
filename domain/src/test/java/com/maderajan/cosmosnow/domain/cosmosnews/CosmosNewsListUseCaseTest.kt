package com.maderajan.cosmosnow.domain.cosmosnews

import app.cash.turbine.test
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
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
            fakeNewsListRepository.articlesFake = listOf(CosmosNews.fake(id = 1, publishedAt = "2023-07-05T22:34:26Z"))
            fakeNewsListRepository.blogFake = listOf(CosmosNews.fake(id = 2, publishedAt = "2024-07-05T22:34:26Z"))
            fakeNewsListRepository.reportsFake = listOf(CosmosNews.fake(id = 3, publishedAt = "2025-07-05T22:34:26Z"))

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
}