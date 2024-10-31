package com.maderajan.cosmosnow.webservice.di

import com.maderajan.cosmosnow.webservice.api.SpaceFlightsNewsApi
import com.maderajan.cosmosnow.webservice.util.OkHttpUtil
import com.maderajan.cosmosnow.webservice.util.RetrofitUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RestModule {

    @Provides
    @Singleton
    internal fun provideArticleApi(okHttpUtil: OkHttpUtil, retrofitUtil: RetrofitUtil): SpaceFlightsNewsApi =
        retrofitUtil.createRetrofit(okHttpUtil.provideHttpClient())
            .create(SpaceFlightsNewsApi::class.java)
}