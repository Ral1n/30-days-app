package com.example.a30_days_app

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface StockService {
    @GET("query")
    suspend fun getStock(
        @Query("function") function: String = "TIME_SERIES_DAILY",
        @Query("symbol") symbol: String,
        @Query("apikey") apikey: String,
    ): StockResponse
}

object StockApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.alphavantage.co/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val stockService: StockService = retrofit.create(StockService::class.java)
}