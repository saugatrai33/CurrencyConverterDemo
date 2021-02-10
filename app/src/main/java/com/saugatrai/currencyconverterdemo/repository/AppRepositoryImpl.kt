package com.saugatrai.currencyconverterdemo.repository

import com.saugatrai.currencyconverterdemo.data.Quote
import com.saugatrai.currencyconverterdemo.data.RemoteApiErrorException
import com.saugatrai.currencyconverterdemo.data.RemoteDataNotFoundException
import com.saugatrai.currencyconverterdemo.data.ResultData
import com.saugatrai.currencyconverterdemo.data_source.RemoteDataSource
import org.json.JSONObject
import timber.log.Timber

class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : AppRepository {

    override suspend fun getCurrency(): ResultData<List<Quote>> {
        return when (val result = remoteDataSource.getCurrency()) {
            is ResultData.Success -> {
                try {
                    val response = JSONObject(result.data)
                    val success = response.getBoolean("success")
                    if (success) {
                        val quotes = response.getJSONObject("quotes")
                        val quoteList: MutableList<Quote> = mutableListOf()
                        val quotesArray = quotes.names()
                        for (i in 0 until quotesArray!!.length()) {
                            val currency = quotesArray.getString(i)
                            val rate = quotes.getString(currency)
                            quoteList.add(Quote(currency = currency, rate = rate.toDouble()))
                        }
                        ResultData.Success(quoteList)
                    } else {
                        val errJson = response.getJSONObject("error")
                        val info = errJson.getString("info")
                        ResultData.Error(RemoteApiErrorException(info))
                    }
                } catch (e: Exception) {
                    ResultData.Error(RemoteApiErrorException(e.localizedMessage))
                }
            }
            is ResultData.Error -> {
                ResultData.Error(RemoteDataNotFoundException())
            }
        }
    }

}