package com.example.a30_days_app.data

import com.example.a30_days_app.data.local.StockCacheEntity
import com.example.a30_days_app.data.local.StockDao
import com.example.a30_days_app.network.StockApiService

interface StockRepository {
    suspend fun getStockPriceToday(symbol: String, apikey: String): Double?
    suspend fun getStockPrice5yAgo(symbol: String, apikey: String): Double?
}

class NetworkStockRepository(
    private val stockApiService: StockApiService,
    private val stockDao: StockDao,
) : StockRepository {

    companion object {
        private const val PRICE_TODAY_TTL_MS = 60 * 60 * 1000L // 1 hour
    }

    override suspend fun getStockPriceToday(symbol: String, apikey: String): Double? {
        val now = System.currentTimeMillis()
        val cached = stockDao.getBySymbol(symbol)

        if (cached != null && now - cached.priceTodayFetchedAt < PRICE_TODAY_TTL_MS) {
            return cached.priceToday
        }

        val fetched = stockApiService.getStockPriceToday(symbol, apikey).firstOrNull()?.price
            ?: return null

        stockDao.upsert(
            cached?.copy(priceToday = fetched, priceTodayFetchedAt = now)
                ?: StockCacheEntity(
                    symbol = symbol,
                    priceToday = fetched,
                    priceTodayFetchedAt = now,
                    price5yAgo = 0.0,
                    price5yAgoFetchedAt = 0L,
                )
        )
        return fetched
    }

    override suspend fun getStockPrice5yAgo(symbol: String, apikey: String): Double? {
        val cached = stockDao.getBySymbol(symbol)

        // Historical data — cache forever once fetched
        if (cached != null && cached.price5yAgoFetchedAt > 0L) {
            return cached.price5yAgo
        }

        val fetched = stockApiService.getStockPrice5yAgo(symbol, apikey).lastOrNull()?.price
            ?: return null

        val now = System.currentTimeMillis()
        stockDao.upsert(
            cached?.copy(price5yAgo = fetched, price5yAgoFetchedAt = now)
                ?: StockCacheEntity(
                    symbol = symbol,
                    priceToday = 0.0,
                    priceTodayFetchedAt = 0L,
                    price5yAgo = fetched,
                    price5yAgoFetchedAt = now,
                )
        )
        return fetched
    }
}
