package com.saugatrai.currencyconverterdemo.data_source.local

import com.saugatrai.currencyconverterdemo.data.ResultData
import com.saugatrai.currencyconverterdemo.data_source.local.db.Quote
import com.saugatrai.currencyconverterdemo.data_source.local.db.QuoteDao
import com.saugatrai.currencyconverterdemo.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Get local quotes
 * */
class LocalDataSourceImpl(
    private val quoteDao: QuoteDao,
    @IoDispatcher private val iODispatcher: CoroutineDispatcher
) : LocalDataSource {

    override suspend fun getQuotes(): ResultData<List<Quote>> =
        withContext(iODispatcher) {
            val quotes = quoteDao.getLocalQuote()
            ResultData.Success(quotes)
        }

    override suspend fun insertQuotes(quotes: List<Quote>) {
        withContext(iODispatcher) {
            quoteDao.insertQuote(quotes)
        }
    }

    override suspend fun deleteAllQuotes() {
        withContext(iODispatcher) {
            quoteDao.deleteAllQuote()
        }
    }
}