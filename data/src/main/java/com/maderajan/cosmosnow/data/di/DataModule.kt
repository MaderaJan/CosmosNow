package com.maderajan.cosmosnow.data.di

import com.maderajan.cosmosnow.data.repository.CosmosNewsRepository
import com.maderajan.cosmosnow.data.repository.ICosmosNewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun provideCosmosNewsRepository(cosmosNewsRepository: CosmosNewsRepository): ICosmosNewsRepository
}