package com.example.a30_days_app.fake

import android.util.Log
import com.example.a30_days_app.data.NetworkStockRepository
import com.example.a30_days_app.model.StockPrice
import org.junit.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class NetworkStockRepositoryTest {
    @Test
    fun networkStockRepositoryTest_getStockPriceToday_verifyStockPricesList() = runTest {
        val repository = NetworkStockRepository(FakeStockApiService())
        assertEquals(
            FakeDataSource.stockPricesTodayList.firstOrNull(),
            repository.getStockPriceToday("FAKE_SYMBOL", "FAKE_API_KEY")
        )
    }

    @Test
    fun networkStockRepositoryTest_getStockPrice5yAgo_verifyStockPricesList() = runTest {
        val repository = NetworkStockRepository(FakeStockApiService())
        assertEquals(
            FakeDataSource.stockPricesTodayList.lastOrNull(),
            repository.getStockPrice5yAgo("FAKE_SYMBOL", "FAKE_API_KEY")
        )
    }


}