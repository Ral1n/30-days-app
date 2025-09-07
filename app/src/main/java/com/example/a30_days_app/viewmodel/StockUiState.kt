package com.example.a30_days_app.viewmodel

import com.example.a30_days_app.model.StockPrice

sealed interface StockUiState {
    data class Success(
        val stockPriceToday: StockPrice,
        val stockPrice5yAgo: StockPrice
    ) : StockUiState

    object Error : StockUiState

    object Loading : StockUiState
}