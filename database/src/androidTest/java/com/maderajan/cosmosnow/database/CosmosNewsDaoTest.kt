package com.maderajan.cosmosnow.database

import app.cash.turbine.test
import com.maderajan.cosmosnow.database.dao.CosmosNewsDao
import com.maderajan.cosmosnow.database.entity.CosmosNewsEntity
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CosmosNewsDaoTest : BaseDatabaseDaoTest() {

    private lateinit var sut: CosmosNewsDao

    override fun setupDao() {
        sut = database.cosmosNewsDao()
    }

    @Test
    fun cosmosNew_Insert_InDatabase() {
        testScope.runTest {
            sut.persist(
                CosmosNewsEntity(
                    id = 123,
                    title = "title",
                    type = "type",
                    summary = "summary",
                    url = "url",
                    newsSite = "newsSite",
                    imageUrl = "imageUrl",
                    publishedAt = "publishedAt",
                )
            )

            sut.selectAll().test {
                assert(awaitItem().isNotEmpty())
            }
        }
    }

    @Test
    fun cosmosNew_Delete_InDatabase() {
        testScope.runTest {
            sut.persist(
                CosmosNewsEntity(
                    id = 123,
                    title = "title",
                    type = "type",
                    summary = "summary",
                    url = "url",
                    newsSite = "newsSite",
                    imageUrl = "imageUrl",
                    publishedAt = "publishedAt",
                )
            )

            sut.selectAll().test {
                assert(awaitItem().isNotEmpty())
            }

            sut.deleteById(123)

            sut.selectAll().test {
                assert(awaitItem().isEmpty())
            }
        }
    }
}