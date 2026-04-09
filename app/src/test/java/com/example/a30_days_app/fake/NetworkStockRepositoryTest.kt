package com.example.a30_days_app.fake

import com.example.a30_days_app.data.NetworkStockRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import kotlin.test.Test

class NetworkStockRepositoryTest {
    @Test
    fun networkStockRepositoryTest_getStockPriceToday_verifyPrice() = runTest {
        val repository = NetworkStockRepository(FakeStockApiService(), FakeStockDao())
        assertEquals(
            FakeDataSource.stockPricesTodayList.firstOrNull()?.price,
            repository.getStockPriceToday("FAKE_SYMBOL", "FAKE_API_KEY")
        )
    }

    @Test
    fun networkStockRepositoryTest_getStockPrice5yAgo_verifyPrice() = runTest {
        val repository = NetworkStockRepository(FakeStockApiService(), FakeStockDao())
        assertEquals(
            FakeDataSource.stockPrices5yAgoList.lastOrNull()?.price,
            repository.getStockPrice5yAgo("FAKE_SYMBOL", "FAKE_API_KEY")
        )
    }
}
