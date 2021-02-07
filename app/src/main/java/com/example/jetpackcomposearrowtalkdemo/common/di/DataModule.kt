package com.example.jetpackcomposearrowtalkdemo.common.di

import android.content.Context
import com.example.data.network.HomeApi
import com.example.data.network.NetworkDataSource
import com.example.data.repository.HomeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by danieh on 02/05/2020
 */
@Module
class DataModule(private val context: Context) {

    @Singleton
    @Provides
    internal fun provideArrowReposRepository(
        network: NetworkDataSource
    ): HomeRepository =
        HomeRepository.invoke(network)

    @Provides
    @Singleton
    internal fun provideNetworkDataSource(client: HomeApi): NetworkDataSource =
        NetworkDataSource.invoke(client)
}
