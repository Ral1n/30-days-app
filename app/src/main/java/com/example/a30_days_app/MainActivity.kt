package com.example.a30_days_app

import android.content.ClipDescription
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a30_days_app.data.Stock
import com.example.a30_days_app.ui.theme._30daysappTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Label
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontStyle
import com.example.a30_days_app.data.stocks
import com.example.a30_days_app.ui.theme.dark_theme_primary
import com.example.a30_days_app.ui.theme.interFamily
import com.example.a30_days_app.ui.theme.positiveGrowthTextColor
import kotlinx.coroutines.delay
import retrofit2.http.Tag
import java.text.SimpleDateFormat
import java.util.Locale

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
    Scaffold(
        topBar = {
            AppTopBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(stocks) {
                StockCard(
                    stock = it,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

suspend fun getStockPriceToday(stock: Stock): String? {
    val response = StockApiPriceToday.priceTodayStockService.getStock(
        symbol = stock.symbol,
        apikey = "MZtuw0KjRdgz4LW7lyunuYzvdgsfWH3t"
    )

    return String.format("%.2f", response.firstOrNull()?.price)
}

suspend fun calculateGrowthOver5y(stock: Stock): String? {
    val response = StockApiPrice5yAgo.price5yAgoStockService.getStock(
        symbol = stock.symbol,
        apikey = "MZtuw0KjRdgz4LW7lyunuYzvdgsfWH3t"
    )

    val stockPriceToday: Double = getStockPriceToday(stock)!!.toDouble()
    val stockPrice5yAgo: Double = response.lastOrNull()!!.price
    Log.d("STOCK", "5yAgoPrice: $stockPrice5yAgo")

    val growthPercentage = (stockPriceToday - stockPrice5yAgo) * 100 / stockPrice5yAgo

    return String.format("%.2f", growthPercentage)
}

@Composable
fun StockCard(stock: Stock, modifier: Modifier = Modifier) {
    var stockPriceToday by remember { mutableStateOf<String?>("") }
    var stockGrowthOver5y by remember { mutableStateOf<String?>("") }

    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        stockPriceToday = getStockPriceToday(stock)
        delay(1000)
        stockGrowthOver5y = calculateGrowthOver5y(stock)
    }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp)
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
                modifier = Modifier.padding(16.dp, 8.dp)
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
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.tertiary,
                                modifier = Modifier
                            )

                            Text(
                                text = if (stockPriceToday.isNullOrEmpty()) {
                                    "..."
                                } else {
                                    "$${stockPriceToday}"
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

                            Text(
                                text = if (stockGrowthOver5y.isNullOrEmpty()) {
                                    "..."
                                } else {
                                    "+$stockGrowthOver5y%"
                                },
                                fontFamily = interFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp,
                                color = positiveGrowthTextColor
                            )
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
                            modifier = Modifier.padding(16.dp, 8.dp)
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
                                bottom = 16.dp,
                                start = 16.dp,
                                end = 16.dp
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
        modifier = Modifier.padding(top = 16.dp)
    )
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