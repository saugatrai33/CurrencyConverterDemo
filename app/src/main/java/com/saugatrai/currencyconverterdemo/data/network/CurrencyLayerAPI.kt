package com.saugatrai.currencyconverterdemo.data.network

import com.saugatrai.currencyconverterdemo.internals.AppConstants
import retrofit2.http.GET

interface CurrencyLayerAPI {

    @GET(AppConstants.CURRENCY_GET)
    suspend fun getCurrency(): String

}