package com.example.a30_days_app.fake

import com.example.a30_days_app.rules.TestDispatcherRule
import com.example.a30_days_app.viewmodel.StockViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class StockViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun stockViewModel_getStockPrices_verifyStockUiStateSuccess() = runTest {
        val stockViewModel = StockViewModel(
            stockRepository = FakeNetworkStockRepository()
        )
        assertEquals(
            "Success: ${FakeDataSource.stockPricesTodayList.size} stock prices retrieved",
            stockViewModel.stockUiState
        )
    }
}