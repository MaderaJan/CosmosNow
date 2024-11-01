package com.maderajan.cosmosnow.data.mapper

import com.maderajan.cosmosnow.data.model.CosmosNews
import com.maderajan.cosmosnow.data.model.CosmosNewsType
import com.maderajan.cosmosnow.webservice.response.SpaceFlightNewsResponse
import javax.inject.Inject

class ReportResponseToCosmosNewsMapper @Inject constructor() : Mapper<SpaceFlightNewsResponse, CosmosNews> {

    override fun map(from: SpaceFlightNewsResponse): CosmosNews =
        from.toData(CosmosNewsType.REPORT)
}