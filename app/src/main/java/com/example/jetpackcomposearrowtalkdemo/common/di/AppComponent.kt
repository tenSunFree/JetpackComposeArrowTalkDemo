package com.example.jetpackcomposearrowtalkdemo.common.di

import com.example.jetpackcomposearrowtalkdemo.common.JCATDApplication
import com.example.jetpackcomposearrowtalkdemo.common.di.viewmodel.ViewModelModule
import com.example.jetpackcomposearrowtalkdemo.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, NetworkModule::class, DataModule::class])
interface AppComponent {

    fun inject(homeFragment: HomeFragment)

    companion object {
        fun create(context: JCATDApplication, url: String, debug: Boolean): AppComponent =
            DaggerAppComponent.builder()
                .networkModule(NetworkModule(url, debug))
                .dataModule(DataModule(context))
                .build()
    }
}
