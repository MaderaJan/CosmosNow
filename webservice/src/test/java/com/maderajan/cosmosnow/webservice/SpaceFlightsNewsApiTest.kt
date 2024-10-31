package com.maderajan.cosmosnow.webservice

import com.maderajan.cosmosnow.webservice.api.SpaceFlightsNewsApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SpaceFlightsNewsApiTest: BaseApiContractTest<SpaceFlightsNewsApi>() {

    override fun createService(): SpaceFlightsNewsApi =
        retrofit.create(SpaceFlightsNewsApi::class.java)

    @Test
    fun getArticlesTest() {
        runTest {
            val articles = api.getArticles()
            assert(articles.results.isNotEmpty())
        }
    }

    @Test
    fun getBlogsTest() {
        runTest {
            val blogs = api.getBlogs()
            assert(blogs.results.isNotEmpty())
        }
    }

    @Test
    fun getReportsTest() {
        runTest {
            val reports = api.getArticles()
            assert(reports.results.isNotEmpty())
        }
    }

    @Test
    fun getInfo() {
        runTest {
            val info = api.getInfo()
            assert(info.newsSites.isNotEmpty())
        }
    }
}