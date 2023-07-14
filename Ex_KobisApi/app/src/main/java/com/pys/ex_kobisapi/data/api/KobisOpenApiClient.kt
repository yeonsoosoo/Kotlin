package com.pys.ex_kobisapi.data.api

import com.pys.ex_kobisapi.data.api.RetrofitClient.mClient
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KobisOpenApiClient {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://kobis.or.kr")
            .client(mClient)
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create()) //sandwich
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val kobisOpenApiService : KobisOpenApiService by lazy {
        retrofit.create(KobisOpenApiService::class.java)
    }
}