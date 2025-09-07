package com.example.a30_days_app.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import com.example.a30_days_app.R

data class Stock(
    val symbol: String,
    @StringRes val titleId: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val descriptionId: Int,
    var priceToday: MutableState<Double>,
    var growth: MutableState<Double>,
)

val stocks = mutableStateListOf(
    Stock(
        symbol = "GOOGL",
        titleId = R.string.stock_title1,
        imageResourceId = R.drawable.google,
        descriptionId = R.string.stock_description1,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "AAPL",
        titleId = R.string.stock_title2,
        imageResourceId = R.drawable.apple,
        descriptionId = R.string.stock_description2,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "AMD",
        titleId = R.string.stock_title3,
        imageResourceId = R.drawable.amd,
        descriptionId = R.string.stock_description3,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "NVDA",
        titleId = R.string.stock_title4,
        imageResourceId = R.drawable.nvidia,
        descriptionId = R.string.stock_description4,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "TSLA",
        titleId = R.string.stock_title5,
        imageResourceId = R.drawable.tesla,
        descriptionId = R.string.stock_description5,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "AMZN",
        titleId = R.string.stock_title6,
        imageResourceId = R.drawable.amazon,
        descriptionId = R.string.stock_description6,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "META",
        titleId = R.string.stock_title7,
        imageResourceId = R.drawable.meta,
        descriptionId = R.string.stock_description7,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "MSFT",
        titleId = R.string.stock_title8,
        imageResourceId = R.drawable.microsoft,
        descriptionId = R.string.stock_description8,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "CSCO",
        titleId = R.string.stock_title9,
        imageResourceId = R.drawable.cisco,
        descriptionId = R.string.stock_description9,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "V",
        titleId = R.string.stock_title10,
        imageResourceId = R.drawable.visa,
        descriptionId = R.string.stock_description10,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "XOM",
        titleId = R.string.stock_title11,
        imageResourceId = R.drawable.exxon,
        descriptionId = R.string.stock_description11,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "NFLX",
        titleId = R.string.stock_title12,
        imageResourceId = R.drawable.netflix,
        descriptionId = R.string.stock_description12,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "PEP",
        titleId = R.string.stock_title13,
        imageResourceId = R.drawable.pepsico,
        descriptionId = R.string.stock_description13,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "KO",
        titleId = R.string.stock_title14,
        imageResourceId = R.drawable.coca_cola,
        descriptionId = R.string.stock_description14,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "WMT",
        titleId = R.string.stock_title15,
        imageResourceId = R.drawable.walmart,
        descriptionId = R.string.stock_description15,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "PLTR",
        titleId = R.string.stock_title16,
        imageResourceId = R.drawable.palantir,
        descriptionId = R.string.stock_description16,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "JPM",
        titleId = R.string.stock_title17,
        imageResourceId = R.drawable.jpmorgan,
        descriptionId = R.string.stock_description17,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "ABBV",
        titleId = R.string.stock_title18,
        imageResourceId = R.drawable.abbvie,
        descriptionId = R.string.stock_description18,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "ADBE",
        titleId = R.string.stock_title19,
        imageResourceId = R.drawable.adobe,
        descriptionId = R.string.stock_description19,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "BAC",
        titleId = R.string.stock_title20,
        imageResourceId = R.drawable.bankofamerica,
        descriptionId = R.string.stock_description20,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "CVX",
        titleId = R.string.stock_title21,
        imageResourceId = R.drawable.chevron,
        descriptionId = R.string.stock_description21,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "GE",
        titleId = R.string.stock_title22,
        imageResourceId = R.drawable.generalelectric,
        descriptionId = R.string.stock_description22,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "BA",
        titleId = R.string.stock_title23,
        imageResourceId = R.drawable.boeing,
        descriptionId = R.string.stock_description23,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "WFC",
        titleId = R.string.stock_title24,
        imageResourceId = R.drawable.wellsfargo,
        descriptionId = R.string.stock_description24,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "PFE",
        titleId = R.string.stock_title25,
        imageResourceId = R.drawable.pfizer,
        descriptionId = R.string.stock_description25,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "COST",
        titleId = R.string.stock_title26,
        imageResourceId = R.drawable.costco,
        descriptionId = R.string.stock_description26,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "SBUX",
        titleId = R.string.stock_title27,
        imageResourceId = R.drawable.starbucks,
        descriptionId = R.string.stock_description27,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "C",
        titleId = R.string.stock_title28,
        imageResourceId = R.drawable.citigroup,
        descriptionId = R.string.stock_description28,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "GS",
        titleId = R.string.stock_title29,
        imageResourceId = R.drawable.goldman,
        descriptionId = R.string.stock_description29,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    ),
    Stock(
        symbol = "UBER",
        titleId = R.string.stock_title30,
        imageResourceId = R.drawable.uber,
        descriptionId = R.string.stock_description30,
        priceToday = mutableDoubleStateOf(0.0),
        growth = mutableDoubleStateOf(0.0)
    )
)