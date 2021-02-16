package com.saugatrai.currencyconverterdemo.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saugatrai.currencyconverterdemo.MainApplication
import com.saugatrai.currencyconverterdemo.R
import com.saugatrai.currencyconverterdemo.ui.adapter.CurrencyAdapter
import com.saugatrai.currencyconverterdemo.utils.hide
import com.saugatrai.currencyconverterdemo.utils.show
import com.saugatrai.currencyconverterdemo.utils.toast
import com.saugatrai.currencyconverterdemo.utils.viewModelProvider
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val appComponents by lazy { MainApplication.appComponents }

    @Inject
    lateinit var currencyViewModel: CurrencyViewModel

    private lateinit var mProgressBar: ProgressBar
    private lateinit var mQuoteList: RecyclerView
    private lateinit var mCurrencyAdapter: CurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponents.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUI()
        initViews()
        setUpObserver()
    }

    private fun initViews() {
        currencyViewModel.getCurrency()
    }

    private fun setUpUI() {
        mProgressBar = findViewById(R.id.progress_bar)
        mQuoteList = findViewById(R.id.quote_list)
    }

    private fun setUpObserver() {
        currencyViewModel.currency.observe(this,
            { quotes ->
                run {
                    mQuoteList.visibility = View.VISIBLE
                    Timber.d(quotes.toString())
                    mQuoteList.apply {
                        setHasFixedSize(true)
                        mCurrencyAdapter = CurrencyAdapter(quotes)
                        layoutManager = GridLayoutManager(this@MainActivity, 2)
                        adapter = mCurrencyAdapter
                    }
                }
            })

        currencyViewModel.errMsg.observe(this,
            { errMsg ->
                run {
                    mQuoteList.visibility = View.GONE
                    Timber.d("error:: $errMsg")
                    this.toast(errMsg)
                }
            })
        currencyViewModel.showLoading.observe(this,
            { showLoading ->
                run {
                    mQuoteList.visibility = View.GONE
                    if (showLoading) mProgressBar.show() else mProgressBar.hide()
                }
            })
    }

}