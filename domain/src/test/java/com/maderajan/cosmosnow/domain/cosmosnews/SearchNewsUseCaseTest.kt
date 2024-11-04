package com.maderajan.cosmosnow.domain.cosmosnews

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import org.junit.Test

class SearchNewsUseCaseTest : BaseUnitTest() {

    private lateinit var sut: SearchNewsUseCase

    override fun setup() {
        sut = SearchNewsUseCase()
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
}