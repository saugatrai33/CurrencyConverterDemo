package com.saugatrai.currencyconverterdemo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saugatrai.currencyconverterdemo.data.Quote
import com.saugatrai.currencyconverterdemo.data.ResultData
import com.saugatrai.currencyconverterdemo.repository.AppRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(private val appRepository: AppRepository) :
    ViewModel() {

    private var _currency = MutableLiveData<List<Quote>>()
    var currency: LiveData<List<Quote>> = _currency

    private var _errMsg = MutableLiveData<String>()
    var errMsg: LiveData<String> = _errMsg

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    fun getCurrency() {
        _showLoading.postValue(true)
        viewModelScope.launch {
            try {
                when (val result = appRepository.getCurrency()) {
                    is ResultData.Success -> {
                        _showLoading.postValue(false)
                        _currency.postValue(result.data)
                    }
                    is ResultData.Error -> {
                        _showLoading.postValue(false)
                        _errMsg.postValue(result.errMSg.toString())
                    }
                }
            } catch (e: Exception) {
                _showLoading.postValue(false)
                _errMsg.postValue(e.localizedMessage)
            }
        }
    }

}