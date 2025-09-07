package com.example.a30_days_app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.a30_days_app.StockApplication
import com.example.a30_days_app.data.Stock
import com.example.a30_days_app.data.StockRepository
import com.example.a30_days_app.data.stocks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import javax.sql.DataSource

class StockViewModel(private val stockRepository: StockRepository) : ViewModel() {
    val stockUiState = MutableStateFlow<List<Stock>>(emptyList())

    init {
        getStocksPricesAndGrowths()
    }

//    val apikey: String = "MZtuw0KjRdgz4LW7lyunuYzvdgsfWH3t"

    fun getStocksPricesAndGrowths() {
        viewModelScope.launch {
            val updatedStocks = stocks.map { stock ->
                val priceToday = stockRepository.getStockPriceToday(
                    stock.symbol,
                    "MZtuw0KjRdgz4LW7lyunuYzvdgsfWH3t"
                )
                val price5yAgo = stockRepository.getStockPrice5yAgo(
                    stock.symbol,
                    "MZtuw0KjRdgz4LW7lyunuYzvdgsfWH3t"
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