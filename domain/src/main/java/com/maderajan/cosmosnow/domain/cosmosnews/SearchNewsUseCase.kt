package com.maderajan.cosmosnow.domain.cosmosnews

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor() {

    fun modifySelectedTypes(isChecked: Boolean, type: CosmosNewsType, currentTypes: List<CosmosNewsType>) =
        if (isChecked) {
            currentTypes + listOf(type)
        } else {
            currentTypes.filter { it != type }
        }
}