package com.maderajan.cosmosnow.webservice.util

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject

class RetrofitUtil @Inject constructor() {

    private val networkJson = Json { ignoreUnknownKeys = true }

    fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl("https://api.spaceflightnewsapi.net/")
            .addConverterFactory(networkJson.asConverterFactory("application/json; charset=UTF8".toMediaType()))
            .client(okHttpClient)

        return builder.build()
    }
}