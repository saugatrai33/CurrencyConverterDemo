package com.saugatrai.currencyconverterdemo.data

sealed class ResultData<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultData<T>()
    data class Error(val errMSg: Exception) : ResultData<Nothing>()
}
