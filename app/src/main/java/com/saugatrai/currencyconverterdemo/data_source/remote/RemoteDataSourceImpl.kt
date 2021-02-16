package com.saugatrai.currencyconverterdemo.data_source.remote

import com.saugatrai.currencyconverterdemo.data.ResultData
import com.saugatrai.currencyconverterdemo.data.network.CurrencyLayerAPI
import com.saugatrai.currencyconverterdemo.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Get quote from server
 * */
class RemoteDataSourceImpl(
    private val currencyLayerAPI: CurrencyLayerAPI,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RemoteDataSource {

    override suspend fun getCurrency(): ResultData<String> =
        withContext(ioDispatcher) {
            val request = currencyLayerAPI.getCurrency()
            ResultData.Success(request)
        }

}