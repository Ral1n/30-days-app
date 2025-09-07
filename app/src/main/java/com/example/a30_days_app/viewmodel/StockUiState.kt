package com.example.a30_days_app.viewmodel

import com.example.a30_days_app.model.StockPrice

sealed interface StockUiState {
    data class Success(
        val stocksPricesToday: List<StockPrice>,
        val stocksPrices5yAgo: List<StockPrice>
    ) : StockUiState

    object Error : StockUiState

    object Loading : StockUiState
}