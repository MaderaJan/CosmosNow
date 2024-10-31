package com.maderajan.cosmosnow.webservice.api

import com.maderajan.cosmosnow.webservice.response.SpaceFlightNewsInfoWrapper
import com.maderajan.cosmosnow.webservice.response.SpaceFlightNewsResponseWrapper
import retrofit2.http.GET

interface SpaceFlightsNewsApi {

    @GET("v4/articles")
    suspend fun getArticles(): SpaceFlightNewsResponseWrapper

    @GET("v4/blogs")
    suspend fun getBlogs(): SpaceFlightNewsResponseWrapper

    @GET("v4/reports")
    suspend fun getReports(): SpaceFlightNewsResponseWrapper

    @GET("v4/info")
    suspend fun getInfo(): SpaceFlightNewsInfoWrapper
}