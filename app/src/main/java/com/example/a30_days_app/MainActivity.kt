package com.example.a30_days_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a30_days_app.data.Stock
import com.example.a30_days_app.ui.theme._30daysappTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.dimensionResource
import com.example.a30_days_app.data.stocks
import com.example.a30_days_app.ui.theme.interFamily
import com.example.a30_days_app.ui.theme.negativeGrowthTextColor
import com.example.a30_days_app.ui.theme.positiveGrowthTextColor
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _30daysappTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    StockOfTheDayApp()
                }
            }
        }
    }
}

@Composable
fun StockOfTheDayApp(modifier: Modifier = Modifier) {

    LaunchedEffect(Unit) {
        for (stock in stocks) {
            stock.priceToday = getStockPriceToday(stock)
            delay(1000)
            stock.growth = calculateGrowthOver5y(stock)
            delay(1000)
            // I added 1 sec delays to make sure the app doesnt exceed the API limit of 5 requests per sec
        }
    }

    Scaffold(
        topBar = {
            AppTopBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(stocks) {
                StockCard(
                    stock = it,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

private suspend fun getStockPriceToday(stock: Stock): Double? {
    val response = StockApiPriceToday.priceTodayStockService.getStock(
        symbol = stock.symbol,
        apikey = "MZtuw0KjRdgz4LW7lyunuYzvdgsfWH3t"
    )

    return response.firstOrNull()?.price
}

private suspend fun calculateGrowthOver5y(stock: Stock): Double? {
    val response = StockApiPrice5yAgo.price5yAgoStockService.getStock(
        symbol = stock.symbol,
        apikey = "MZtuw0KjRdgz4LW7lyunuYzvdgsfWH3t"
    )

    val stockPriceToday: Double = getStockPriceToday(stock)!!

    // Last element in this api call is exactly the stock 5y ago
    val stockPrice5yAgo: Double = response.lastOrNull()!!.price

    val growthPercentage = (stockPriceToday - stockPrice5yAgo) * 100 / stockPrice5yAgo

    return growthPercentage
}

@Composable
fun StockCard(stock: Stock, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                dimensionResource(R.dimen.padding_big),
                dimensionResource(R.dimen.padding_medium)
            )
    ) {
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(
                    dimensionResource(R.dimen.padding_big),
                    dimensionResource(R.dimen.padding_medium)
                )
            ) {
                Text(
                    text = stringResource(stock.titleId),
                    style = MaterialTheme.typography.headlineMedium,
                    fontFamily = interFamily,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Text(
                    text = " (${stock.symbol})",
                    style = MaterialTheme.typography.headlineSmall,
                    fontFamily = interFamily,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(bottom = 0.dp, top = 0.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(stock.imageResourceId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small))
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
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.tertiary,
                                modifier = Modifier
                            )

                            Text(
                                text = if (stock.priceToday == null) {
                                    "..."
                                } else {
                                    String.format("$%.2f", stock.priceToday)
                                },
                                fontFamily = interFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onSecondary,
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
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.tertiary,
                                modifier = Modifier
                            )

                            val stockGrowth = stock.growth

                            if (stockGrowth == null) {
                                Text(
                                    text = "...",
                                    fontFamily = interFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp,
                                    color = MaterialTheme.colorScheme.onSecondary,
                                )
                            } else if (stockGrowth < 0) {
                                Text(
                                    text = String.format("%.2f%%", stockGrowth),
                                    fontFamily = interFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp,
                                    color = negativeGrowthTextColor
                                )
                            } else {
                                Text(
                                    text = String.format("+%.2f%%", stockGrowth),
                                    fontFamily = interFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp,
                                    color = positiveGrowthTextColor
                                )
                            }
                        }

                        Spacer(modifier = Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "* the presented data was retrieved using Financial Modeling Prep API",
                        fontFamily = interFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 9.sp,
                        color = MaterialTheme.colorScheme.tertiary,
                        lineHeight = 14.sp,
                        modifier = Modifier
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
            }

            Surface(
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Description",
                            fontFamily = interFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSecondary,
                            modifier = Modifier.padding(
                                dimensionResource(R.dimen.padding_big),
                                dimensionResource(R.dimen.padding_medium)
                            )
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(
                            onClick = {
                                expanded = !expanded
                            },
                            modifier = Modifier
                        ) {
                            Icon(
                                imageVector = if (expanded) {
                                    Icons.Filled.ExpandLess
                                } else {
                                    Icons.Filled.ExpandMore
                                },
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSecondary,
                                modifier = Modifier.size(width = 20.dp, height = 20.dp)
                            )
                        }
                    }

                    if (expanded) {
                        Text(
                            text = stringResource(stock.descriptionId),
                            fontFamily = interFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.padding(
                                top = 0.dp,
                                bottom = dimensionResource(R.dimen.padding_big),
                                start = dimensionResource(R.dimen.padding_big),
                                end = dimensionResource(R.dimen.padding_big)
                            )
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        CenterAlignedTopAppBar(
            title = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "STOCK OF THE DAY",
                        fontFamily = interFamily,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 28.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )

                    Text(
                        text = "Five years can change everything. Start today.",
                        fontFamily = interFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_big))
        )
    }
}

@Preview
@Composable
fun LightStockOfTheDayAppPreview() {
    _30daysappTheme(
        darkTheme = false,
    ) {
        StockOfTheDayApp()
    }
}

@Preview
@Composable
fun DarkStockOfTheDayAppPreview() {
    _30daysappTheme(
        darkTheme = true,
    ) {
        StockOfTheDayApp()
    }
}