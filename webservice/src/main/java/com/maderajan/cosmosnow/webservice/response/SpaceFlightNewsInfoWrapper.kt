package com.maderajan.cosmosnow.webservice.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpaceFlightNewsInfoWrapper(
    @SerialName("news_sites")
    val newsSites: List<String>
)