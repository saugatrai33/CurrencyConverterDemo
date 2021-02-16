package com.saugatrai.currencyconverterdemo.data_source.local

import com.saugatrai.currencyconverterdemo.data.ResultData
import com.saugatrai.currencyconverterdemo.data_source.local.db.Quote

interface LocalDataSource {

    suspend fun getQuotes(): ResultData<List<Quote>>

    suspend fun insertQuotes(quotes: List<Quote>)

    suspend fun deleteAllQuotes()

}