package com.example.a30_days_app.fake

import com.example.a30_days_app.rules.TestDispatcherRule
import com.example.a30_days_app.viewmodel.StockUiState
import com.example.a30_days_app.viewmodel.StockViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class StockViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun stockViewModel_loadStocks_uiStateIsSuccess() = runTest {
        val stockViewModel = StockViewModel(
            stockRepository = FakeNetworkStockRepository()
        )
        assertTrue(stockViewModel.stockUiState.value is StockUiState.Success)
    }
}
