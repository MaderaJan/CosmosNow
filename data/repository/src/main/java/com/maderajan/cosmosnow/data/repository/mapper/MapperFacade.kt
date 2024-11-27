package com.maderajan.cosmosnow.data.repository.mapper

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.repository.mapper.cosmosnews.ArticleResponseToCosmosNewsMapper
import com.maderajan.cosmosnow.data.repository.mapper.cosmosnews.BlogResponseToCosmosNewsMapper
import com.maderajan.cosmosnow.data.repository.mapper.cosmosnews.CosmosNewsEntityToCosmosNewsMapper
import com.maderajan.cosmosnow.data.repository.mapper.cosmosnews.CosmosNewsToCosmosNewsEntityMapper
import com.maderajan.cosmosnow.data.repository.mapper.cosmosnews.ReportResponseToCosmosNewsMapper
import com.maderajan.cosmosnow.database.entity.CosmosNewsEntity
import javax.inject.Inject

class MapperFacade @Inject constructor(
    val cosmosNewsToCosmosNewsEntityMapper: CosmosNewsToCosmosNewsEntityMapper,
    val cosmosNewsEntityToCosmosNewsMapper: CosmosNewsEntityToCosmosNewsMapper,
    val articleResponseToCosmosNewsMapper: ArticleResponseToCosmosNewsMapper,
    val blogResponseToCosmosNewsMapper: BlogResponseToCosmosNewsMapper,
    val reportResponseToCosmosNewsMapper: ReportResponseToCosmosNewsMapper
) {

    inline fun <reified I, reified O> map(input: I): O {
        val mapper = getMapper<I, O>() as Mapper<I, O>
        return mapper.map(input)
    }

    inline fun <reified I, reified O> getMapper(): Mapper<out Any, out Any> =
        when {
            I::class.java == CosmosNews::class.java && O::class.java == CosmosNewsEntity::class.java -> {
                cosmosNewsToCosmosNewsEntityMapper
            }

            I::class.java == CosmosNewsEntity::class.java && O::class.java == CosmosNews::class.java -> {
                cosmosNewsEntityToCosmosNewsMapper
            }

            else -> throw IllegalArgumentException("No mapper found for input ${I::class.java}, output ${O::class.java} ")
        }
}