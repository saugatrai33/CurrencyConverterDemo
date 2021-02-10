package com.saugatrai.currencyconverterdemo.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saugatrai.currencyconverterdemo.R

class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val currencyText: TextView = itemView.findViewById(R.id.text_currency)
    val rate: TextView = itemView.findViewById(R.id.text_rate)
}