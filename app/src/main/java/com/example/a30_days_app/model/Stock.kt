package com.example.a30_days_app.model

import android.content.res.Resources
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.a30_days_app.R

data class Stock(
    val symbol: String,
    @StringRes val titleId: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val descriptionId: Int
) {
}

val stocks = listOf<Stock>(
    Stock(
        symbol = "GOOGL",
        titleId = R.string.stock_title1,
        imageResourceId = R.drawable.google,
        descriptionId = R.string.stock_description1
    ),
    Stock(
        symbol = "AAPL",
        titleId = R.string.stock_title2,
        imageResourceId = R.drawable.apple,
        descriptionId = R.string.stock_description2
    ),
    Stock(
        symbol = "AMD",
        titleId = R.string.stock_title3,
        imageResourceId = R.drawable.amd,
        descriptionId = R.string.stock_description3
    ),
)