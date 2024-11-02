package com.maderajan.cosmosnow.webservice.api

import com.maderajan.cosmosnow.webservice.response.SpaceFlightNewsInfoWrapper
import com.maderajan.cosmosnow.webservice.response.SpaceFlightNewsResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceFlightsNewsApi {

    @GET("v4/articles")
    suspend fun getArticles(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20
    ): SpaceFlightNewsResponseWrapper

    @GET("v4/blogs")
    suspend fun getBlogs(): SpaceFlightNewsResponseWrapper

    @GET("v4/reports")
    suspend fun getReports(): SpaceFlightNewsResponseWrapper

    @GET("v4/info")
    suspend fun getInfo(): SpaceFlightNewsInfoWrapper
}