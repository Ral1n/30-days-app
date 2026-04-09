package com.example.a30_days_app.fake

import com.example.a30_days_app.data.local.StockCacheEntity
import com.example.a30_days_app.data.local.StockDao

class FakeStockDao : StockDao {
    private val cache = mutableMapOf<String, StockCacheEntity>()

    override suspend fun getBySymbol(symbol: String): StockCacheEntity? = cache[symbol]

    override suspend fun upsert(entity: StockCacheEntity) {
        cache[entity.symbol] = entity
    }
}
