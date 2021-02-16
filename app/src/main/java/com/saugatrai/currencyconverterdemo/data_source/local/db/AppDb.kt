package com.saugatrai.currencyconverterdemo.data_source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Quote::class], version = 1, exportSchema = false)
@TypeConverters(QuoteTypeConverter::class)
abstract class AppDb : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

}