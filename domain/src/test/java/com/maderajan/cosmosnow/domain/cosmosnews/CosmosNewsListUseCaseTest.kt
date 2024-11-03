package com.maderajan.cosmosnow.domain.cosmosnews

import app.cash.turbine.test
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CosmosNewsListUseCaseTest: BaseUnitTest() {

    private lateinit var sut: CosmosNewsListUseCase
    private lateinit var fakeCosmosNewsListRepository: FakeCosmosNewsListRepository

    @Before
    override fun setup() {
        fakeCosmosNewsListRepository = FakeCosmosNewsListRepository()

        sut = CosmosNewsListUseCase(
            cosmosNewsRepository = fakeCosmosNewsListRepository
        )
    }

    @Test
    fun areNews_Sorted_ByDescending() {
        fakeCosmosNewsListRepository.articlesFake = listOf(CosmosNews.fake(id = 1, publishedAt = "2023-07-05T22:34:26Z"))
        fakeCosmosNewsListRepository.blogFake = listOf(CosmosNews.fake(id = 2, publishedAt = "2024-07-05T22:34:26Z"))
        fakeCosmosNewsListRepository.reportsFake = listOf(CosmosNews.fake(id = 3, publishedAt = "2025-07-05T22:34:26Z"))

        testScope.runTest {
            sut.getSortedNews().test {
                val sortedNews = awaitItem()
                assert(sortedNews[0].id == 3L && sortedNews[1].id == 2L && sortedNews[2].id == 1L)
                awaitComplete()
            }
        }
    }
}