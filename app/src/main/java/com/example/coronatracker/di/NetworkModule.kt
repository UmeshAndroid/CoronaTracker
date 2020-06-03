package com.example.coronatracker.di

import android.content.Context
import androidx.annotation.NonNull
import com.example.coronatracker.data.network.ApiServices
import com.example.coronatracker.data.network.NetworkConnectionInterceptor
import com.example.coronatracker.data.network.OkHttpClientProvider
import com.example.coronatracker.data.network.RetrofitProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule() {

    @Provides
    fun provideNetworkConnectionInterceptor(context: Context) = NetworkConnectionInterceptor(context)

    @Provides
    fun provideOkHttpClientProvider(networkConnectionInterceptor: NetworkConnectionInterceptor, headerInterceptor: Interceptor) = OkHttpClientProvider(networkConnectionInterceptor,headerInterceptor)

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return  GsonBuilder()
            .setLenient()
            .disableHtmlEscaping().create()
    }
    @Provides
    fun provideOKHttpClient (okHttpClientProvider: OkHttpClientProvider) = okHttpClientProvider.get()

    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) = RetrofitProvider(gson = gson, okHttpClient =  okHttpClient ).get()

    @Provides
    @Singleton
    fun getHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request =
                chain.request().newBuilder()
                    .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "6e3c8430eemsh78a85cf98ea3bb1p1b0f6fjsn337427cc5c0e")

            val actualRequest = request.build()
            chain.proceed(actualRequest)
        }
    }
    @Provides
    @Singleton
    fun provideApiService(@NonNull retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }

}