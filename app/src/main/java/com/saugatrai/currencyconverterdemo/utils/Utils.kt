package com.saugatrai.currencyconverterdemo.utils

import com.saugatrai.currencyconverterdemo.data.RemoteApiErrorException
import com.saugatrai.currencyconverterdemo.data.RemoteDataNotFoundException
import com.saugatrai.currencyconverterdemo.data.ResultData
import com.saugatrai.currencyconverterdemo.data_source.local.LocalDataSource
import com.saugatrai.currencyconverterdemo.data_source.local.db.Quote
import com.saugatrai.currencyconverterdemo.data_source.remote.RemoteDataSource
import org.json.JSONObject

object Utils {

    /**
     * Parse quotes from jason object
     *
     * @param response currency response from server
     *
     * @return List of {Quote}
     * */
    fun parseCurrency(response: JSONObject): List<Quote> {

        val quotes = response.getJSONObject("quotes")
        val quoteList: MutableList<Quote> = mutableListOf()
        val quotesArray = quotes.names()
        for (i in 0 until quotesArray!!.length()) {
            val currency = quotesArray.getString(i)
            val rate = quotes.getString(currency)
            quoteList.add(Quote(currency = currency, rate = rate.toDouble()))
        }
        return quoteList

    }

    /**
     * Parse response from json object
     *
     * @param response currency response from server
     *
     * @return the error message
     * */
    fun parseError(response: JSONObject): String {
        val errJson = response.getJSONObject("error")
        return errJson.getString("info")
    }

}