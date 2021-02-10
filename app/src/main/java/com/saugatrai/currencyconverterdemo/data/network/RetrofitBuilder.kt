package com.saugatrai.currencyconverterdemo.data.network

import com.saugatrai.currencyconverterdemo.internals.AppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter(AppConstants.ACCESS_KEY, AppConstants.ACCESS_VALUE)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val currencyLayerClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .client(currencyLayerClient)
        .baseUrl(AppConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val currencyLayerApi: CurrencyLayerAPI = retrofit().create(CurrencyLayerAPI::class.java)

}