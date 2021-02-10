package com.saugatrai.currencyconverterdemo.data_source

import com.saugatrai.currencyconverterdemo.data.ResultData
import com.saugatrai.currencyconverterdemo.model.QuoteResponse
import org.json.JSONObject

interface RemoteDataSource {
    suspend fun getCurrency(): ResultData<String>
}