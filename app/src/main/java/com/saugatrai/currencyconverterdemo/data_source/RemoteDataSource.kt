package com.saugatrai.currencyconverterdemo.data_source

import com.saugatrai.currencyconverterdemo.data.ResultData

interface RemoteDataSource {
    suspend fun getCurrency(): ResultData<String>
}