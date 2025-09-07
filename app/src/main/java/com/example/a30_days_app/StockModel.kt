//package com.example.a30_days_app
//
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.GET
//import retrofit2.http.Path
//import retrofit2.http.Query
//import java.util.Date
//
//interface PriceTodayStockService {
//    @GET("quote-short")
//    suspend fun getStock(
//        @Query("symbol") symbol: String,
//        @Query("apikey") apikey: String,
//    ): List<PriceTodayStockResponse>
//}
//
//interface Price5yAgoStockService {
//    @GET("historical-price-eod/light")
//    suspend fun getStock(
//        @Query("symbol") symbol: String,
//        @Query("apikey") apikey: String,
//    ): List<Price5yAgoStockResponse>
//}
//
//object StockApiPriceToday {
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("https://financialmodelingprep.com/stable/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    val priceTodayStockService: PriceTodayStockService =
//        retrofit.create(PriceTodayStockService::class.java)
//}
//
//object StockApiPrice5yAgo {
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("https://financialmodelingprep.com/stable/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    val price5yAgoStockService: Price5yAgoStockService =
//        retrofit.create(Price5yAgoStockService::class.java)
//}
//
