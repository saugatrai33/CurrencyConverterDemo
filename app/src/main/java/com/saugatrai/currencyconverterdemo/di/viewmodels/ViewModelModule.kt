package com.saugatrai.currencyconverterdemo.di.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saugatrai.currencyconverterdemo.ui.main.CurrencyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyViewModel::class)
    abstract fun buildCurrencyViewModel(currencyViewModel: CurrencyViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

}