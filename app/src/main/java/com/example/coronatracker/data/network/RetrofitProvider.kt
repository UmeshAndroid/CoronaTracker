package com.example.coronatracker.data.network

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Provider

class RetrofitProvider(private val gson: Gson, private val okHttpClient: OkHttpClient): Provider<Retrofit> {

    override fun get(): Retrofit  = Retrofit.Builder().
        baseUrl("https://coronavirus-monitor.p.rapidapi.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}