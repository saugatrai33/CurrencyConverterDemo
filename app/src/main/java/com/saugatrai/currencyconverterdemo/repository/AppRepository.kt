package com.saugatrai.currencyconverterdemo.repository

import com.saugatrai.currencyconverterdemo.data_source.local.db.Quote
import com.saugatrai.currencyconverterdemo.data.ResultData

interface AppRepository {
    suspend fun getCurrency(): ResultData<List<Quote>>
}