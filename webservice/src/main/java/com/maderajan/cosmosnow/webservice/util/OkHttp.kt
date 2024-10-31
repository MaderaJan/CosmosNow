package com.maderajan.cosmosnow.webservice.util

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class OkHttpUtil @Inject constructor() {

    companion object {
        private const val TIMEOUT = 30L
    }

    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        addInterceptor(interceptor)

        connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        readTimeout(TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(TIMEOUT, TimeUnit.SECONDS)
    }.build()
}