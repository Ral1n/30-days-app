package com.example.a30_days_app.fake

import com.example.a30_days_app.data.StockRepository
import com.example.a30_days_app.model.StockPrice

class FakeNetworkStockRepository : StockRepository {
    override suspend fun getStockPriceToday(symbol: String, apikey: String): StockPrice? {
        return FakeDataSource.stockPricesTodayList.firstOrNull()
    }

    override suspend fun getStockPrice5yAgo(symbol: String, apikey: String): StockPrice? {
        return FakeDataSource.stockPrices5yAgoList.lastOrNull()
    }
}