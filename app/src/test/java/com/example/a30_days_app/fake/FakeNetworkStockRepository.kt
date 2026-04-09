package com.example.a30_days_app.fake

import com.example.a30_days_app.data.StockRepository

class FakeNetworkStockRepository : StockRepository {
    override suspend fun getStockPriceToday(symbol: String, apikey: String): Double? {
        return FakeDataSource.stockPricesTodayList.firstOrNull()?.price
    }

    override suspend fun getStockPrice5yAgo(symbol: String, apikey: String): Double? {
        return FakeDataSource.stockPrices5yAgoList.lastOrNull()?.price
    }
}
