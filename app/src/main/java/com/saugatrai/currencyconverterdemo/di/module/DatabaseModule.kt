package com.saugatrai.currencyconverterdemo.di.module

import android.app.Application
import androidx.room.Room
import com.saugatrai.currencyconverterdemo.data_source.local.db.AppDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(application: Application) {

    private val appDb = Room.databaseBuilder(
        application,
        AppDb::class.java,
        "quote.db"
    ).build()

    @Provides
    @Singleton
    fun provideDb(): AppDb = appDb

    @Provides
    @Singleton
    fun provideQuoteDao(appDb: AppDb) = appDb.quoteDao()

}