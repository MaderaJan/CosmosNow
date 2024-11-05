package com.maderajan.cosmosnow.domain.cosmosnews

import com.maderajan.cosmosnow.data.repository.cosmosnews.ICosmosNewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsFontUseCase @Inject constructor(
    private val cosmosNewsRepository: ICosmosNewsRepository
) {

    companion object {
        private const val MIN_FONT_SIZE = 12
        private const val MAX_FONT_SIZE = 30
    }

    fun getFontSize(): Flow<Int> =
        cosmosNewsRepository.getFontSize()

    suspend fun increaseFont(currentFontSize: Int) {
        if (currentFontSize < MAX_FONT_SIZE) {
            val updatedFontSize = currentFontSize + 2
            cosmosNewsRepository.updateFontSize(updatedFontSize)
        }
    }

    suspend fun decreaseFont(currentFontSize: Int) {
        if (currentFontSize > MIN_FONT_SIZE) {
            val updatedFontSize = currentFontSize - 2
            cosmosNewsRepository.updateFontSize(updatedFontSize)
        }
    }
}