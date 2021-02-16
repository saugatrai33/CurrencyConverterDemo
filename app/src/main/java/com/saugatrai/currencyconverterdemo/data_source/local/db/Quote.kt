package com.saugatrai.currencyconverterdemo.data_source.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote")
data class Quote(

    @PrimaryKey(autoGenerate = false)
    val currency: String,

    val rate: Double
)