package com.saugatrai.currencyconverterdemo.di.component

import android.content.Context
import com.saugatrai.currencyconverterdemo.ui.main.MainActivity
import com.saugatrai.currencyconverterdemo.di.module.AppModule
import com.saugatrai.currencyconverterdemo.di.module.CoroutinesModule
import com.saugatrai.currencyconverterdemo.di.module.NetworkModule
import com.saugatrai.currencyconverterdemo.di.module.RepositoryModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        CoroutinesModule::class]
)
interface AppComponents {

    fun context(): Context

    fun retrofit(): Retrofit

    fun inject(mainActivity: MainActivity)
}