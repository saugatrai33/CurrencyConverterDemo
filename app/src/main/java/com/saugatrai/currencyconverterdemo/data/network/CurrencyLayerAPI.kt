package com.saugatrai.currencyconverterdemo.data.network

import com.saugatrai.currencyconverterdemo.internals.AppConstants
import com.saugatrai.currencyconverterdemo.model.QuoteResponse
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyLayerAPI {

    @GET(AppConstants.CURRENCY_GET)
    suspend fun getCurrency(): String

}