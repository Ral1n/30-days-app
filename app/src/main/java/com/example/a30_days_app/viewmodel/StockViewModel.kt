package com.example.a30_days_app.viewmodel

import androidx.compose.runtime.getValue
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
import com.example.a30_days_app.data.StockRepository
import kotlinx.coroutines.launch
import okhttp3.Call
import okio.IOException

class StockViewModel(private val stockRepository: StockRepository) : ViewModel() {
    var stockUiState: StockUiState by mutableStateOf(StockUiState.Loading)
        private set

    init {
        val symbols = stockRepository.getAllSymbols()
        symbols.forEach {
            getStocks(it)
        }
    }

    private val apikey: String = "MZtuw0KjRdgz4LW7lyunuYzvdgsfWH3t"

    fun getStocks(symbol: String) {
        viewModelScope.launch {
            stockUiState = StockUiState.Loading
            stockUiState = try {
                val pricesToday = stockRepository.getStocksPricesToday(symbol, apikey)
                val prices5yAgo = stockRepository.getStocksPrices5yAgo(symbol, apikey)
                StockUiState.Success(pricesToday, prices5yAgo)
            } catch (e: IOException) {
                StockUiState.Error
            } catch (e: HttpException) {
                StockUiState.Error
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