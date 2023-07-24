package com.example.abcd777.app

import android.app.Application
import com.example.abcd777.di.AppComponent
import com.example.abcd777.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
