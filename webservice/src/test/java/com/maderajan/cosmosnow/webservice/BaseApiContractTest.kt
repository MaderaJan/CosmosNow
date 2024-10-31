package com.maderajan.cosmosnow.webservice

import com.maderajan.cosmosnow.webservice.util.OkHttpUtil
import com.maderajan.cosmosnow.webservice.util.RetrofitUtil
import org.junit.Before
import retrofit2.Retrofit

abstract class BaseApiContractTest<T : Any> {

    protected lateinit var api: T
    protected lateinit var retrofit: Retrofit

    @Before
    fun setup() {
        retrofit = RetrofitUtil().createRetrofit(OkHttpUtil().provideHttpClient())
        api = createService()
    }

    abstract fun createService(): T
}