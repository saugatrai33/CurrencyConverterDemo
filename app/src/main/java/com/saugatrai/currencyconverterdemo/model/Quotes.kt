package com.saugatrai.currencyconverterdemo.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Quotes(
    @SerializedName("USDAED")
    val uSDAED: Double,
    @SerializedName("USDAFN")
    val uSDAFN: Double,
    @SerializedName("USDALL")
    val uSDALL: Double,
    @SerializedName("USDAMD")
    val uSDAMD: Double,
    @SerializedName("USDANG")
    val uSDANG: Double,
    @SerializedName("USDAOA")
    val uSDAOA: Double,
    @SerializedName("USDARS")
    val uSDARS: Double,
    @SerializedName("USDAUD")
    val uSDAUD: Double,
    @SerializedName("USDAWG")
    val uSDAWG: Double,
    @SerializedName("USDAZN")
    val uSDAZN: Double,
    @SerializedName("USDBAM")
    val uSDBAM: Double
)