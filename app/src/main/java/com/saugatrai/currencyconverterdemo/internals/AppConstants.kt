package com.saugatrai.currencyconverterdemo.internals

import com.saugatrai.currencyconverterdemo.BuildConfig

object AppConstants {
    const val BASE_URL = "http://api.currencylayer.com/"
    private const val CURRENCY_CONVERTER_LIVE = "live"
    const val CURRENCY_GET = "${BASE_URL}${CURRENCY_CONVERTER_LIVE}"
    const val ACCESS_KEY = "access_key"
    const val ACCESS_VALUE = BuildConfig.CURRENCYLAYER_ACCESS_KEY

    // Network config
    const val IO_TIMEOUT = 30L
}