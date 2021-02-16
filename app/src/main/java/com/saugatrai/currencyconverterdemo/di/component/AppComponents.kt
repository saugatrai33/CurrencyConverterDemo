package com.saugatrai.currencyconverterdemo.di.component

import android.content.Context
import com.saugatrai.currencyconverterdemo.data_source.local.db.AppDb
import com.saugatrai.currencyconverterdemo.data_source.local.db.QuoteDao
import com.saugatrai.currencyconverterdemo.di.module.*
import com.saugatrai.currencyconverterdemo.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        CoroutinesModule::class,
    DatabaseModule::class]
)
interface AppComponents {

    fun context(): Context

    fun appDb(): AppDb

    fun quoteDao(): QuoteDao

    fun inject(mainActivity: MainActivity)
}