package com.saugatrai.currencyconverterdemo.data_source.local.db

import androidx.room.*

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote ORDER BY currency DESC")
    suspend fun getLocalQuote(): List<Quote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quotes: List<Quote>)

    @Query("DELETE FROM quote")
    suspend fun deleteAllQuote()

}