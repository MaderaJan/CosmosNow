package com.maderajan.cosmosnow.database

import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

class CosmosNowMigrationTest {

    companion object {
        private const val TEST_DB_NAME = "fake-cosmos-now.dn"
    }

    @get:Rule
    val helper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(), CosmosNowDatabase::class.java, listOf(), FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    fun run_AllMigrationInSerialProcess_Success() {
        val db = helper.createDatabase(TEST_DB_NAME, 1)
        db.close()

        helper.runMigrationsAndValidate(TEST_DB_NAME, 2, true)
    }
}