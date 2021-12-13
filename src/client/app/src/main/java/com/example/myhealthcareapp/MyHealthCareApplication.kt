package com.example.myhealthcareapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyHealthCareApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyHealthCareApplication)
            modules(myHealthCareModule)
        }

    }
}