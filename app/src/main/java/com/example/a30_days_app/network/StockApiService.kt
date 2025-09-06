package com.example.a30_days_app.network

import com.example.a30_days_app.model.StockPrice
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApiService {
    @GET(value = "quote-short")
    suspend fun getStockPriceToday(
        @Query("symbol") symbol: String,
        @Query("apikey") apikey: String,
    ): List<StockPrice>

    @GET(value = "historical-price-eod/light")
    suspend fun getStockPrice5yAgo(
        @Query("symbol") symbol: String,
        @Query("apikey") apikey: String,
    ): List<StockPrice>
}