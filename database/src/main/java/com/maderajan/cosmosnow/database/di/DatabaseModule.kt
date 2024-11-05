package com.maderajan.cosmosnow.database.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.maderajan.cosmosnow.database.CosmosNowDatabase
import com.maderajan.cosmosnow.database.dao.CosmosNewsDao
import com.maderajan.cosmosnow.database.preferences.PreferencesStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    private val Context.preferencesStorage by preferencesDataStore(PreferencesStorage.NAME)

    @Provides
    @Singleton
    internal fun providePreferencesStorage(@ApplicationContext context: Context) : PreferencesStorage =
        PreferencesStorage(context.preferencesStorage)

    @Provides
    @Singleton
    internal fun provideCosmosNowDatabase(@ApplicationContext context: Context): CosmosNowDatabase =
        CosmosNowDatabase.create(context)

    @Provides
    internal fun provideCosmosNewsDao(database: CosmosNowDatabase): CosmosNewsDao =
        database.cosmosNewsDao()
}