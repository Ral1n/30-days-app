package com.example.a30_days_app.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.a30_days_app.R

data class Stock(
    val symbol: String,
    @StringRes val titleId: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val descriptionId: Int,
    val priceToday: Double = 0.0,
    val growth: Double = 0.0,
)

val stockCatalog = listOf(
    Stock(symbol = "GOOGL", titleId = R.string.stock_title1, imageResourceId = R.drawable.google, descriptionId = R.string.stock_description1),
    Stock(symbol = "AAPL", titleId = R.string.stock_title2, imageResourceId = R.drawable.apple, descriptionId = R.string.stock_description2),
    Stock(symbol = "AMD", titleId = R.string.stock_title3, imageResourceId = R.drawable.amd, descriptionId = R.string.stock_description3),
    Stock(symbol = "NVDA", titleId = R.string.stock_title4, imageResourceId = R.drawable.nvidia, descriptionId = R.string.stock_description4),
    Stock(symbol = "TSLA", titleId = R.string.stock_title5, imageResourceId = R.drawable.tesla, descriptionId = R.string.stock_description5),
    Stock(symbol = "AMZN", titleId = R.string.stock_title6, imageResourceId = R.drawable.amazon, descriptionId = R.string.stock_description6),
    Stock(symbol = "META", titleId = R.string.stock_title7, imageResourceId = R.drawable.meta, descriptionId = R.string.stock_description7),
)
