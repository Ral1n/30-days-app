package com.example.a30_days_app.fake

import com.example.a30_days_app.model.StockPrice

object FakeDataSource {
    const val price1 = 9.99
    const val price2 = 19.99
    const val price3 = 29.99
    const val price4 = 39.99
    const val price5 = 49.99
    const val price6 = 59.99
    const val price7 = 69.99
    const val price8 = 79.99
    const val price9 = 89.99
    const val price10 = 99.99

    val stockPricesTodayList = listOf(
        StockPrice(price = price1),
        StockPrice(price = price2),
        StockPrice(price = price3),
        StockPrice(price = price4),
        StockPrice(price = price5),
    )

    val stockPrices5yAgoList = listOf(
        StockPrice(price = price6),
        StockPrice(price = price7),
        StockPrice(price = price8),
        StockPrice(price = price9),
        StockPrice(price = price10),
    )
}