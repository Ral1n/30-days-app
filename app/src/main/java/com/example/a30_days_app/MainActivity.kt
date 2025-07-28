package com.example.a30_days_app

import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
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
import com.example.a30_days_app.model.Stock
import com.example.a30_days_app.ui.theme.BebasNeue
import com.example.a30_days_app.ui.theme.GoogleSans
import com.example.a30_days_app.ui.theme.Monda
import com.example.a30_days_app.ui.theme.Monserrat
import com.example.a30_days_app.ui.theme._30daysappTheme
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _30daysappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StockCard(
                        stock = Stock(symbol = "GOOGL", titleId = R.string.stock_title1, imageResourceId = R.drawable.google, descriptionId = R.string.stock_description1),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
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

    LaunchedEffect(Unit){
        closePrice = getLatestClosePriceForLastDay(stock)
    }

    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
            .height(200.dp)
    ){
        Column {
            Text(
                text = stringResource(stock.titleId),
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = Monserrat,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )

            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(stock.imageResourceId),
                    contentDescription = null,
                    modifier = Modifier.size(width = 160.dp, height = 160.dp)
                        .padding(8.dp)
                )

                Row (
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Column (
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        Text(
                            text = "price \ntoday",
                            fontFamily = Monserrat,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                        )

                        Text(
                            text = closePrice?: "...",
                            fontFamily = BebasNeue,
                            fontSize = 32.sp,
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Column (
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        Text(
                            text = "5 years \ngrowth",
                            fontFamily = Monserrat,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                        )

                        Text(
                            text = "17%",
                            fontFamily = BebasNeue,
                            fontSize = 32.sp,
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.weight(1f))



                Spacer(modifier = Modifier.weight(1f))
            }
        }


//        Text(
//            text = stringResource(stock.descriptionId),
//            style = MaterialTheme.typography.bodyMedium,
//            modifier = Modifier.padding(16.dp)
//        )
    }
}

@Preview(showBackground = true)
@Composable
fun StockCardPreview() {
    _30daysappTheme {
        StockCard(Stock(symbol = "GOOGL", titleId = R.string.stock_title1, imageResourceId = R.drawable.google, descriptionId = R.string.stock_description1))
    }
}