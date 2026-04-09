package com.example.a30_days_app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock_cache")
data class StockCacheEntity(
    @PrimaryKey val symbol: String,
    val priceToday: Double,
    val priceTodayFetchedAt: Long,
    val price5yAgo: Double,
    val price5yAgoFetchedAt: Long,
)
