package com.saugatrai.currencyconverterdemo.repository

import com.saugatrai.currencyconverterdemo.data.Quote
import com.saugatrai.currencyconverterdemo.data.ResultData

interface AppRepository {
    suspend fun getCurrency(): ResultData<List<Quote>>
}