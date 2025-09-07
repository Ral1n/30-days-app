package com.example.a30_days_app.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StockPrice(
    @SerialName(value = "price")
    val price: Double
)