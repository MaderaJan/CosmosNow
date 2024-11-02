package com.maderajan.cosmosnow.domain.cosmosnews

import app.cash.turbine.test
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
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
        fakeCosmosNewsListRepository.articlesFake = listOf(fakeCosmosNews(id = 1, date = "2023-07-05T22:34:26Z"))
        fakeCosmosNewsListRepository.blogFake = listOf(fakeCosmosNews(id = 2, date = "2024-07-05T22:34:26Z"))
        fakeCosmosNewsListRepository.reportsFake = listOf(fakeCosmosNews(id = 3, date = "2025-07-05T22:34:26Z"))

        testScope.runTest {
            sut.getSortedNews().test {
                val sortedNews = awaitItem()
                assert(sortedNews[0].id == 3L && sortedNews[1].id == 2L && sortedNews[2].id == 1L)
                awaitComplete()
            }
        }
    }

    private fun fakeCosmosNews(id: Long, date: String) =
        CosmosNews(
            id = id,
            title = "",
            type = CosmosNewsType.ARTICLE,
            newsSite = "",
            imageUrl = "",
            publishedAt = date,
        )
}