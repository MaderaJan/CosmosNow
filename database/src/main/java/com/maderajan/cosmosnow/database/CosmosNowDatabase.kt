package com.maderajan.cosmosnow.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maderajan.cosmosnow.database.dao.CosmosNewsDao
import com.maderajan.cosmosnow.database.entity.CosmosNewsEntity

@Database(
    entities = [CosmosNewsEntity::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(
            from = 1,
            to = 2,
        ),
    ]
)
abstract class CosmosNowDatabase: RoomDatabase() {

    companion object {
        private const val NAME = "cosmos-now.db"

        fun create(context: Context): CosmosNowDatabase =
            Room.databaseBuilder(context, CosmosNowDatabase::class.java, NAME)
                .build()
    }

    abstract fun cosmosNewsDao(): CosmosNewsDao
}