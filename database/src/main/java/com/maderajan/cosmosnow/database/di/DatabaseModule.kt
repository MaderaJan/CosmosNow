package com.maderajan.cosmosnow.database.di

import android.content.Context
import com.maderajan.cosmosnow.database.CosmosNowDatabase
import com.maderajan.cosmosnow.database.dao.CosmosNewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    internal fun provideCosmosNowDatabase(@ApplicationContext context: Context): CosmosNowDatabase =
        CosmosNowDatabase.create(context)

    @Provides
    internal fun provideCosmosNewsDao(database: CosmosNowDatabase): CosmosNewsDao =
        database.cosmosNewsDao()
}