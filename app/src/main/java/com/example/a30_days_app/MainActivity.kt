package com.example.a30_days_app

import android.content.ClipData
import android.hardware.biometrics.PromptContentItemBulletedText
import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a30_days_app.data.Datasource
import com.example.a30_days_app.model.Stock
import com.example.a30_days_app.ui.theme.BebasNeue
import com.example.a30_days_app.ui.theme.GoogleSans
import com.example.a30_days_app.ui.theme.Monda
import com.example.a30_days_app.ui.theme._30daysappTheme
import java.nio.file.WatchEvent
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.font.Font
import com.example.a30_days_app.ui.theme.LightGrayBackground
import com.example.a30_days_app.ui.theme.RedColor
import com.example.a30_days_app.ui.theme.Secondary
import com.example.a30_days_app.ui.theme.interFamily
import com.example.a30_days_app.ui.theme.labelText
import com.example.a30_days_app.ui.theme.montserratFamily
import com.example.a30_days_app.ui.theme.onPrimary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _30daysappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StockOfTheDayApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun StockOfTheDayApp(modifier: Modifier = Modifier) {
    _30daysappTheme {
        Scaffold { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightGrayBackground)
            ) {
                StockList(
                    stockList = Datasource().loadStocks(),
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

suspend fun getLatestClosePriceForLastDay(stock: Stock): String? {
    val response = StockApi.stockService.getStock(
        symbol = stock.symbol,
        apikey = "81ZQS5CDRLQ52ZV1",
    )

    if (response.timeSeries == null) {
        return null
    }

    return response.timeSeries.values.firstOrNull()?.get("4. close")
}

suspend fun calculate5YearsGrowth(stock: Stock): String? {
    val response = StockApi.stockService.getStock(
        symbol = stock.symbol,
        apikey = "81ZQS5CDRLQ52ZV1",
    )

    if (response.timeSeries == null) {
        return null
    }

    //TODO - De obtinut pretul de acum 5 ani din aceasi zi

    //TODO - De calculat procentul cu care a crescut
    //TODO - x = todayPrice - 5yearsAgoPrice
    //TODO - procent = x * 100 / 5yearsAgoPrice
    return null
}

@Composable
fun StockCard(stock: Stock, modifier: Modifier = Modifier) {
    var closePrice by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        closePrice = getLatestClosePriceForLastDay(stock)
    }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp)
//            .height(200.dp)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp, 8.dp)
            ) {
                Text(
                    text = stringResource(stock.titleId),
                    style = MaterialTheme.typography.headlineMedium,
                    fontFamily = interFamily,
                    fontWeight = FontWeight.ExtraBold,
//                  modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = " (${stock.symbol})",
                    style = MaterialTheme.typography.headlineSmall,
                    fontFamily = interFamily,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(bottom = 0.dp, top = 0.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(stock.imageResourceId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 96.dp, height = 96.dp)
                        .padding(4.dp)
                )

                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = "price today",
                                fontFamily = interFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                color = labelText,
                                modifier = Modifier
                            )

                            Text(
                                text = closePrice ?: "...",
                                fontFamily = interFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 30.sp,
                                color = onPrimary
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = "5 years growth",
                                fontFamily = interFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                color = labelText,
                                modifier = Modifier
                            )

                            Text(
                                text = "17%",
                                fontFamily = interFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 30.sp,
                                color = onPrimary
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "*the presented data was retrieved using Alpha Vantage API",
                        fontFamily = interFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 7.5.sp,
                        color = labelText,
                        lineHeight = 14.sp,
                        modifier = Modifier
                    )
                }


                Spacer(modifier = Modifier.weight(1f))
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    color = Secondary,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Description",
                        fontFamily = interFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = onPrimary,
                        modifier = Modifier.padding(16.dp, 8.dp)
                    )
                }
            }
        }

//        Text(
//            text = stringResource(stock.descriptionId),
//            style = MaterialTheme.typography.bodyMedium,
//            modifier = Modifier.padding(16.dp)
//        )
    }
}

@Composable
private fun StockList(stockList: List<Stock>, modifier: Modifier = Modifier) {
    LazyColumn(
//        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(stockList) { stock ->
            StockCard(stock)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StockCardPreview() {
    _30daysappTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(LightGrayBackground)
        ) {
            StockOfTheDayApp()
        }
    }
}