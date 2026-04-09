package com.example.a30_days_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a30_days_app.BuildConfig
import com.example.a30_days_app.StockApplication
import com.example.a30_days_app.data.Stock
import com.example.a30_days_app.data.StockRepository
import com.example.a30_days_app.data.stockCatalog
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface StockUiState {
    data object Loading : StockUiState
    data class Success(val stocks: List<Stock>) : StockUiState
    data object Error : StockUiState
}

class StockViewModel(private val stockRepository: StockRepository) : ViewModel() {

    private val _stockUiState = MutableStateFlow<StockUiState>(StockUiState.Loading)
    val stockUiState: StateFlow<StockUiState> = _stockUiState.asStateFlow()

    init {
        loadStocks()
    }

    fun loadStocks() {
        _stockUiState.value = StockUiState.Loading
        viewModelScope.launch {
            try {
                coroutineScope {
                    val result = stockCatalog.map { stock ->
                        async {
                            val priceToday = stockRepository.getStockPriceToday(
                                stock.symbol,
                                BuildConfig.API_KEY
                            )
                            val price5yAgo = stockRepository.getStockPrice5yAgo(
                                stock.symbol,
                                BuildConfig.API_KEY
                            )
                            if (priceToday != null && price5yAgo != null) {
                                stock.copy(
                                    priceToday = priceToday,
                                    growth = (priceToday - price5yAgo) * 100 / price5yAgo
                                )
                            } else {
                                stock
                            }
                        }
                    }.awaitAll()
                    _stockUiState.value = StockUiState.Success(result)
                }
            } catch (e: Exception) {
                _stockUiState.value = StockUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as StockApplication)
                val stockRepository = application.container.stockRepository
                StockViewModel(stockRepository = stockRepository)
            }
        }
    }
}
