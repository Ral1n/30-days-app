package com.example.a30_days_app.data

import android.util.Log
import coil.decode.DataSource
import com.example.a30_days_app.model.StockPrice
import com.example.a30_days_app.network.StockApiService

interface StockRepository {
    suspend fun getStockPriceToday(symbol: String, apikey: String): StockPrice?
    suspend fun getStockPrice5yAgo(symbol: String, apikey: String): StockPrice?
}

class NetworkStockRepository(
    private val stockApiService: StockApiService
) : StockRepository {
    override suspend fun getStockPriceToday(symbol: String, apikey: String): StockPrice? {
        Log.d("TEST", "Requesting price for symbol: $symbol with apikey: $apikey")
        return stockApiService.getStockPriceToday(symbol, apikey).firstOrNull()
    }

    override suspend fun getStockPrice5yAgo(symbol: String, apikey: String): StockPrice? {
        Log.d("TEST", "Requesting price for symbol: $symbol with apikey: $apikey")
        return stockApiService.getStockPrice5yAgo(symbol, apikey).lastOrNull()
    }
}
