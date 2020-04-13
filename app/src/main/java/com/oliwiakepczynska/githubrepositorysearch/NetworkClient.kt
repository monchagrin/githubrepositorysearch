package com.oliwiakepczynska.githubrepositorysearch

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class NetworkClient {

    companion object {

        fun create(baseUrl: String = "https://api.github.com"): NetworkService {
            val client = OkHttpClient
                .Builder()
                .addInterceptor(
                    HttpLoggingInterceptor()
                        .apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                .build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(NetworkService::class.java)
        }
    }
}
