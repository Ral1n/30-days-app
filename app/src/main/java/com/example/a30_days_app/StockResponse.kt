package com.example.a30_days_app

import com.google.gson.annotations.SerializedName

data class StockResponse(
    @SerializedName("Time Series (Daily)")
    val timeSeries: Map<String, Map<String, String>>?
)

