package com.saugatrai.currencyconverterdemo.di.module

import android.app.Application
import android.content.Context
import com.saugatrai.currencyconverterdemo.di.viewmodels.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun providesContext(): Context = application
}