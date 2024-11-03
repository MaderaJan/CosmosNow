package com.maderajan.cosmosnow.data.repository.di

import com.maderajan.cosmosnow.data.repository.bookmarks.BookmarkRepository
import com.maderajan.cosmosnow.data.repository.bookmarks.IBookmarkRepository
import com.maderajan.cosmosnow.data.repository.cosmosnews.CosmosNewsRepository
import com.maderajan.cosmosnow.data.repository.cosmosnews.ICosmosNewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun provideCosmosNewsRepository(cosmosNewsRepository: CosmosNewsRepository): ICosmosNewsRepository

    @Binds
    abstract fun provideBookmarkRepository(bookmarkRepository: BookmarkRepository): IBookmarkRepository
}