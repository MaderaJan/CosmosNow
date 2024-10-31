package com.maderajan.cosmosnow.webservice

import com.maderajan.cosmosnow.webservice.api.ArticleApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ArticleApiTest: BaseApiContractTest<ArticleApi>() {

    override fun createService(): ArticleApi =
        retrofit.create(ArticleApi::class.java)

    @Test
    fun getArticlesTest() {
        runTest {
            val articles = api.getArticles()
            assert(articles.results.isNotEmpty())
        }
    }
}