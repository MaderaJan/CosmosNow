package com.maderajan.cosmosnow.webservice.api

import com.maderajan.cosmosnow.webservice.response.ArticleResponseWrapper
import retrofit2.http.GET

interface ArticleApi {

    @GET("v4/articles")
    suspend fun getArticles(): ArticleResponseWrapper
}

