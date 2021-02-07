package com.example.jetpackcomposearrowtalkdemo.common.di

import com.example.data.network.ApplicationJsonAdapterFactory
import com.example.data.network.HomeApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by danieh on 02/05/2020
 */
@Module
class NetworkModule(private val url: String, private val debug: Boolean) {

    @Provides
    @Singleton
    fun providesMoshi(): Moshi = Moshi.Builder()
        .add(ApplicationJsonAdapterFactory.INSTANCE)
        .build()

    @Provides
    @Singleton
    fun providesMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    internal fun provideOkHttpClient() = OkHttpClient.Builder().apply {
        if (debug) {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
            addInterceptor(loggingInterceptor)
        }
    }.build()

    @Provides
    @Singleton
    internal fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ) =
        Retrofit.Builder().apply {
            baseUrl(url)
            client(okHttpClient)
            addConverterFactory(moshiConverterFactory)
        }.build()

    @Provides
    @Singleton
    internal fun provideHomeApi(retrofit: Retrofit): HomeApi =
        retrofit.create(HomeApi::class.java)
}
