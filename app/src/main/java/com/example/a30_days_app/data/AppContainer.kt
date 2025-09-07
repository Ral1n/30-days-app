package com.example.a30_days_app.data

import com.example.a30_days_app.network.StockApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val stockRepository: StockRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://financialmodelingprep.com/stable/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: StockApiService by lazy {
        retrofit.create(StockApiService::class.java)
    }

    override val stockRepository: StockRepository by lazy {
        NetworkStockRepository(retrofitService)
    }
}