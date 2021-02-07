package com.example.jetpackcomposearrowtalkdemo.common

import android.app.Application
import com.example.jetpackcomposearrowtalkdemo.BuildConfig
import com.example.jetpackcomposearrowtalkdemo.common.di.AppComponent

class JCATDApplication : Application() {

    val appComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        AppComponent.create(this,
            BASE_URL,
            BuildConfig.DEBUG
        )
    }

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}
