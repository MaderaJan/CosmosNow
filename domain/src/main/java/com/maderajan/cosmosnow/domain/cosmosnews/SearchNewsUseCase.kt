package com.maderajan.cosmosnow.domain.cosmosnews

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import com.maderajan.cosmosnow.data.repository.cosmosnews.ICosmosNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val cosmosNewsRepository: ICosmosNewsRepository
) {

    // TODO TEST
    fun getNewsSitesOrderByName(): Flow<List<String>> = flow {
        emit(cosmosNewsRepository.getInfo().sorted())
    }

    fun modifySelectedTypes(isChecked: Boolean, type: CosmosNewsType, currentTypes: List<CosmosNewsType>) =
        if (isChecked) {
            currentTypes + listOf(type)
        } else {
            currentTypes.filter { it != type }
        }

    // TODO TEST
    fun modifySelectedSites(isChecked: Boolean, site: String, selectedSites: List<String>): List<String> =
        if (isChecked) {
            selectedSites + listOf(site)
        } else {
            selectedSites.filter { it != site }
        }
}