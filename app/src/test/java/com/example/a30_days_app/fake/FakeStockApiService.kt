package com.example.a30_days_app.fake

import com.example.a30_days_app.model.StockPrice
import com.example.a30_days_app.network.StockApiService

class FakeStockApiService : StockApiService {
    override suspend fun getStockPriceToday(symbol: String, apikey: String): List<StockPrice> {
        return FakeDataSource.stockPricesTodayList
    }

    override suspend fun getStockPrice5yAgo(symbol: String, apikey: String): List<StockPrice> {
        return FakeDataSource.stockPrices5yAgoList
    }
}