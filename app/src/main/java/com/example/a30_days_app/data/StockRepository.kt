package com.example.a30_days_app.data

import com.example.a30_days_app.model.StockPrice
import com.example.a30_days_app.network.StockApiService

interface StockRepository {
    suspend fun getStocksPricesToday(symbol: String, apikey: String): List<StockPrice>
    suspend fun getStocksPrices5yAgo(symbol: String, apikey: String): List<StockPrice>
}

class NetworkStockRepository(
    private val stockApiService: StockApiService
) : StockRepository {
    override suspend fun getStocksPricesToday(symbol: String, apikey: String): List<StockPrice> {
        return stockApiService.getStockPriceToday(symbol, apikey)
    }

    override suspend fun getStocksPrices5yAgo(symbol: String, apikey: String): List<StockPrice> {
        return stockApiService.getStockPrice5yAgo(symbol, apikey)
    }
}
