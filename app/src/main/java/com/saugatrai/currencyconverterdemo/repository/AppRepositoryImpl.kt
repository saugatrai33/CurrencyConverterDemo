package com.saugatrai.currencyconverterdemo.repository

import com.saugatrai.currencyconverterdemo.data.RemoteApiErrorException
import com.saugatrai.currencyconverterdemo.data.RemoteDataNotFoundException
import com.saugatrai.currencyconverterdemo.data.ResultData
import com.saugatrai.currencyconverterdemo.data_source.local.LocalDataSource
import com.saugatrai.currencyconverterdemo.data_source.local.db.Quote
import com.saugatrai.currencyconverterdemo.data_source.remote.RemoteDataSource
import com.saugatrai.currencyconverterdemo.utils.Utils
import org.json.JSONObject

/**
 * Get or send data to local db or servers.
 * */
class AppRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : AppRepository {

    override suspend fun getCurrency(): ResultData<List<Quote>> {
        return when (val result = remoteDataSource.getCurrency()) {
            is ResultData.Success -> {
                try {
                    val response = JSONObject(result.data)
                    val success = response.getBoolean("success")
                    if (success) {
                        val quoteList = Utils.parseCurrency(response)
                        localDataSource.deleteAllQuotes()
                        localDataSource.insertQuotes(quoteList)
                        ResultData.Success(quoteList)
                    } else {
                        val errMsg = Utils.parseError(response)
                        ResultData.Error(RemoteApiErrorException(errMsg))
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