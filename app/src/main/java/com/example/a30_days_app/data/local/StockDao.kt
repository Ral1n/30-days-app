package com.example.a30_days_app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StockDao {
    @Query("SELECT * FROM stock_cache WHERE symbol = :symbol")
    suspend fun getBySymbol(symbol: String): StockCacheEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: StockCacheEntity)
}
