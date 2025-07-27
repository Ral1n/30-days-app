package com.example.a30_days_app.data

import com.example.a30_days_app.model.Stock
import com.example.a30_days_app.R

class Datasource {
    fun loadStocks(): List<Stock>{
        return listOf<Stock>(
            Stock(titleId = R.string.stock_title1, imageResourceId = R.drawable.google, descriptionId = R.string.stock_description1),
            Stock(titleId = R.string.stock_title2, imageResourceId = R.drawable.apple, descriptionId = R.string.stock_description2)
        )
    }
}