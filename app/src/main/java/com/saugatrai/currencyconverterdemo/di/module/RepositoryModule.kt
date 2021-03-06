package com.saugatrai.currencyconverterdemo.di.module

import com.saugatrai.currencyconverterdemo.data.network.CurrencyLayerAPI
import com.saugatrai.currencyconverterdemo.data_source.local.LocalDataSourceImpl
import com.saugatrai.currencyconverterdemo.data_source.local.db.QuoteDao
import com.saugatrai.currencyconverterdemo.data_source.remote.RemoteDataSourceImpl
import com.saugatrai.currencyconverterdemo.di.IoDispatcher
import com.saugatrai.currencyconverterdemo.repository.AppRepository
import com.saugatrai.currencyconverterdemo.repository.AppRepositoryImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAppRepository(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        api: CurrencyLayerAPI,
        quoteDao: QuoteDao
    ): AppRepository {
        val localDataSource = LocalDataSourceImpl(quoteDao, ioDispatcher)
        val remoteDataSource = RemoteDataSourceImpl(api, ioDispatcher)
        return AppRepositoryImpl(localDataSource, remoteDataSource)
    }

}