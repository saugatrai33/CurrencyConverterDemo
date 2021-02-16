package com.saugatrai.currencyconverterdemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saugatrai.currencyconverterdemo.R
import com.saugatrai.currencyconverterdemo.data_source.local.db.Quote

class CurrencyAdapter(private val quotes: List<Quote>) :
    RecyclerView.Adapter<CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_currency, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val quote = quotes[position]
        holder.currencyText.text = quote.currency
        holder.rate.text = quote.rate.toString()
    }

    override fun getItemCount(): Int = quotes.size

}