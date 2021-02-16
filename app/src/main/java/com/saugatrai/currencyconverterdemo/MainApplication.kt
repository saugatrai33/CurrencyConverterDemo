package com.saugatrai.currencyconverterdemo

import android.app.Application
import com.facebook.stetho.Stetho
import com.saugatrai.currencyconverterdemo.di.component.AppComponents
import com.saugatrai.currencyconverterdemo.di.component.DaggerAppComponents
import com.saugatrai.currencyconverterdemo.di.module.AppModule
import com.saugatrai.currencyconverterdemo.di.module.DatabaseModule
import timber.log.Timber

class MainApplication : Application() {

    companion object {
        lateinit var appComponents: AppComponents
    }

    override fun onCreate() {
        super.onCreate()
        appComponents = initDagger(this)
        initStetho()
        initTimber()
    }

    private fun initDagger(app: MainApplication): AppComponents =
        DaggerAppComponents.builder()
            .appModule(AppModule(app))
            .databaseModule(DatabaseModule(app))
            .build()


    private fun initStetho() {
        if (com.facebook.stetho.BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}