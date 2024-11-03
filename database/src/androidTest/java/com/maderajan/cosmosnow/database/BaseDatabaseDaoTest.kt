package com.maderajan.cosmosnow.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.test.TestScope
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseDatabaseDaoTest {

    protected lateinit var database: CosmosNowDatabase
    protected val testScope = TestScope()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        database = Room.inMemoryDatabaseBuilder(context, CosmosNowDatabase::class.java)
            .build()

        setupDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    abstract fun setupDao()
}
