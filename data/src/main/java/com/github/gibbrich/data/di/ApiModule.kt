package com.github.gibbrich.data.di

import com.github.gibbrich.data.BuildConfig
import com.github.gibbrich.data.network.Api
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class ApiModule {
    companion object {
        private const val CONNECT_TIMEOUT_MILLIS = 15_000L
        private const val READ_TIMEOUT_MILLIS = 30_000L
        private const val WRITE_TIMEOUT_MILLIS = 15_000L


        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @DataScope
    @Provides
    fun providePosApi(client: OkHttpClient): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Reusable
    @Provides
    fun provideClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.HEADERS
        }

        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .addInterceptor(logger)
            .readTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .build()
    }
}