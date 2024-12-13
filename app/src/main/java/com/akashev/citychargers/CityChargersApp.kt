package com.akashev.citychargers

import android.app.Application
import com.akashev.citychargers.di.cityChargersModule
import org.koin.core.context.startKoin

class CityChargersApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(cityChargersModule)
        }
    }
}