package com.saugatrai.currencyconverterdemo.data

open class DataSourceException(errMsg: String? = null) : Exception(errMsg)

class RemoteDataNotFoundException : DataSourceException("Data not found in remote data source.")

class RemoteApiErrorException(apiErrMsg: String? = null) :
    DataSourceException(apiErrMsg)