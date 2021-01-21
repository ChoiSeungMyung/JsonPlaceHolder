package com.victorizzie.android.apps.jsonplaceholder.worker

import com.squareup.moshi.Moshi
import com.victorizzie.android.apps.jsonplaceholder.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitWorker {
    private val okhttp by lazy {
        OkHttpClient.Builder().build()
    }

    private val moshi by lazy {
        Moshi.Builder().build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(okhttp)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }
}