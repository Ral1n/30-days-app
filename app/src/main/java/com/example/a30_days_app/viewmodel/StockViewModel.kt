package com.example.a30_days_app.viewmodel

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a30_days_app.BuildConfig.API_KEY
import com.example.a30_days_app.StockApplication
import com.example.a30_days_app.data.Stock
import com.example.a30_days_app.data.StockRepository
import com.example.a30_days_app.data.stocks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class StockViewModel(private val stockRepository: StockRepository) : ViewModel() {
    val stockUiState = MutableStateFlow<List<Stock>>(emptyList())

    init {
        getStocksPricesAndGrowths()
    }

    fun getStocksPricesAndGrowths() {
        viewModelScope.launch {
            val updatedStocks = stocks.map { stock ->
                val priceToday = stockRepository.getStockPriceToday(
                    stock.symbol,
                    API_KEY
                )
                val price5yAgo = stockRepository.getStockPrice5yAgo(
                    stock.symbol,
                    API_KEY
                )

                if (priceToday != null && price5yAgo != null) {
                    stock.copy(
                        priceToday = mutableDoubleStateOf(priceToday.price),
                        growth = mutableDoubleStateOf((priceToday.price - price5yAgo.price) * 100 / price5yAgo.price)
                    )
                } else {
                    stock
                }
            }

            stockUiState.value = updatedStocks
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