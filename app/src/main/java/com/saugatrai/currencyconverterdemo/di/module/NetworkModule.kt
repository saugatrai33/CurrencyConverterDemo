package com.saugatrai.currencyconverterdemo.di.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.saugatrai.currencyconverterdemo.BuildConfig
import com.saugatrai.currencyconverterdemo.data.network.CurrencyLayerAPI
import com.saugatrai.currencyconverterdemo.internals.AppConstants
import com.saugatrai.currencyconverterdemo.internals.AppConstants.BASE_URL
import com.saugatrai.currencyconverterdemo.internals.AppConstants.IO_TIMEOUT
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        scalarsConverterFactory: ScalarsConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(scalarsConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {

        val authInterceptor = Interceptor { chain ->
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

        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(IO_TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG)
            client.addNetworkInterceptor(StethoInterceptor())
        return client.build()
    }

    @Provides
    @Singleton
    fun providesConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideScalarConverterFactory(): ScalarsConverterFactory =
        ScalarsConverterFactory.create()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CurrencyLayerAPI =
        retrofit.create(CurrencyLayerAPI::class.java)
}