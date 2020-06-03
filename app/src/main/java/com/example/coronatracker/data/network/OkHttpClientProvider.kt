package com.example.coronatracker.data.network

import com.example.coronatracker.BuildConfig
import com.example.coronatracker.di.Environment
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class OkHttpClientProvider(private val networkConnectionInterceptor: NetworkConnectionInterceptor, private val headerInterceptor: Interceptor) {
    fun get(): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(headerInterceptor)
            .addLoggingInterceptor()

        if(Environment.MOCK == Environment.valueOf(BuildConfig.FLAVOR.toUpperCase())){
            httpClientBuilder.addInterceptor(MockInterceptor())
        }
        httpClientBuilder.addInterceptor(networkConnectionInterceptor)
        return  httpClientBuilder.build()
    }
}