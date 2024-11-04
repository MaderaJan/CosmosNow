package com.maderajan.cosmosnow.domain.cosmosnews

import app.cash.turbine.test
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import com.maderajan.cosmosnow.domain.cosmosnews.fake.FakeCosmosNewsListRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SearchNewsUseCaseTest : BaseUnitTest() {

    private lateinit var sut: SearchNewsUseCase
    private lateinit var fakeCosmosNewsListRepository: FakeCosmosNewsListRepository

    override fun setup() {
        fakeCosmosNewsListRepository = FakeCosmosNewsListRepository()

        sut = SearchNewsUseCase(
            cosmosNewsRepository = fakeCosmosNewsListRepository
        )
    }

    @Test
    fun isTypeAdded_WhenIsChecked() {
        val updatedTypes = sut.modifySelectedTypes(isChecked = true, type = CosmosNewsType.ARTICLE, currentTypes = emptyList())
        assert(
            updatedTypes.size == 1 && updatedTypes.first() == CosmosNewsType.ARTICLE
        )
    }

    @Test
    fun isTypeRemoved_WhenUnChecked() {
        val currentTypes = listOf(CosmosNewsType.ARTICLE)

        assert(currentTypes.size == 1 && currentTypes.first() == CosmosNewsType.ARTICLE)

        val updatedTypes = sut.modifySelectedTypes(isChecked = false, type = CosmosNewsType.ARTICLE, currentTypes = currentTypes)
        assert(updatedTypes.isEmpty())
    }

    @Test
    fun areNewsSitesOrdered_ByName() {
        fakeCosmosNewsListRepository.sitesFake = listOf("Z", "NASA", "CNBC", "Europa")
        val expectedList = listOf("CNBC", "Europa", "NASA", "Z")

        testScope.runTest {
            sut.getNewsSitesOrderByName().test {
                assert(expectedList == awaitItem())
                awaitComplete()
            }
        }
    }

    @Test
    fun isNewsAdded_WhenIsChecked() {
        val updatedNews = sut.modifySelectedSites(isChecked = true, site = "NASA", selectedSites = emptyList())
        assert(
            updatedNews.size == 1 && updatedNews.first() == "NASA"
        )
    }

    @Test
    fun isNewsRemoved_WhenUnChecked() {
        val updatedNews = sut.modifySelectedSites(isChecked = false, site = "NASA", selectedSites = listOf("NASA"))
        assert(
            updatedNews.isEmpty()
        )
    }
}