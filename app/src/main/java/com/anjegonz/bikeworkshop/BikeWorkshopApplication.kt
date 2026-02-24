package com.anjegonz.bikeworkshop

import android.app.Application
import com.anjegonz.bikeworkshop.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BikeWorkshopApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@BikeWorkshopApplication)
            modules(koinModule)
        }
    }
}