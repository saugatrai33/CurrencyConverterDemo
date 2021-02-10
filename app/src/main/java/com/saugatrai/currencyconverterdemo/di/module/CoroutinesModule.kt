package com.saugatrai.currencyconverterdemo.di.module

import com.saugatrai.currencyconverterdemo.di.DefaultDispatcher
import com.saugatrai.currencyconverterdemo.di.IoDispatcher
import com.saugatrai.currencyconverterdemo.di.MainDispatcher
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object CoroutinesModule {
    @DefaultDispatcher
    @JvmStatic
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @JvmStatic
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @JvmStatic
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}