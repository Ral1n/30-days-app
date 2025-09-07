package com.example.a30_days_app

import android.app.Application
import com.example.a30_days_app.data.AppContainer
import com.example.a30_days_app.data.DefaultAppContainer

class StockApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
