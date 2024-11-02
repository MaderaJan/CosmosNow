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
            val articles = api.getArticles(limit = 1)
            assert(articles.results.isNotEmpty())
        }
    }

    @Test
    fun getBlogsTest() {
        runTest {
            val blogs = api.getBlogs(limit = 1)
            assert(blogs.results.isNotEmpty())
        }
    }

    @Test
    fun getReportsTest() {
        runTest {
            val reports = api.getReports(limit = 1)
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