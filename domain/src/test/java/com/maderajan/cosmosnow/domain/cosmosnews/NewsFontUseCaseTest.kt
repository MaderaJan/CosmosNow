package com.maderajan.cosmosnow.domain.cosmosnews

import app.cash.turbine.test
import com.maderajan.cosmosnow.domain.cosmosnews.fake.FakeCosmosNewsListRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NewsFontUseCaseTest : BaseUnitTest() {

    private lateinit var sut: NewsFontUseCase
    private lateinit var fakeCosmosNewsRepository: FakeCosmosNewsListRepository

    override fun setup() {
        fakeCosmosNewsRepository = FakeCosmosNewsListRepository()

        sut = NewsFontUseCase(
            cosmosNewsRepository = fakeCosmosNewsRepository
        )
    }

    @Test
    fun isFontIncreased_WhenIsBetween_MinMax_Bounds() {
        testIncreaseDecreaseScenario(
            currentSize = 16,
            expected = 18,
            operation = sut::increaseFont
        )
    }

    @Test
    fun isFontDecreased_WhenIsBetween_MinMax_Bounds() {
        testIncreaseDecreaseScenario(
            currentSize = 16,
            expected = 14,
            operation = sut::decreaseFont
        )
    }

    @Test
    fun isFontNotIncreased_WhenIs_MaxSize() {
        testIncreaseDecreaseScenario(
            currentSize = 30,
            expected = 30,
            operation = sut::increaseFont
        )
    }

    @Test
    fun isFontNotIncreased_WhenIs_MinSize() {
        testIncreaseDecreaseScenario(
            currentSize = 12,
            expected = 12,
            operation = sut::decreaseFont
        )
    }

    private fun testIncreaseDecreaseScenario(
        currentSize: Int,
        expected: Int,
        operation: suspend (Int) -> Unit
    ) {
        fakeCosmosNewsRepository.fontSize = currentSize

        testScope.runTest {
            operation(currentSize)

            sut.getFontSize().test {
                assert(awaitItem() == expected)
                awaitComplete()
            }
        }
    }

}