package com.example.a30_days_app.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a30_days_app.R
import com.example.a30_days_app.data.Stock
import com.example.a30_days_app.data.stocks
import com.example.a30_days_app.model.StockPrice
import com.example.a30_days_app.ui.theme._30daysappTheme
import com.example.a30_days_app.ui.theme.interFamily
import com.example.a30_days_app.ui.theme.negativeGrowthTextColor
import com.example.a30_days_app.ui.theme.positiveGrowthTextColor
import com.example.a30_days_app.viewmodel.StockViewModel
import kotlinx.coroutines.delay


@Composable
fun MainScreen(
    viewModel: StockViewModel = viewModel(),
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val stocks by viewModel.stockUiState.collectAsState()
    StockCardsScreen(stocks = stocks, modifier = modifier.padding(contentPadding))
//    when (stockUiState) {
//        is StockUiState.Loading -> LoadingScreen(
//            modifier = modifier.fillMaxWidth()
//        )
//
//        is StockUiState.Success -> StockCardsScreen(
//            stocks = stockUiState.stocks,
//            modifier = modifier.padding(contentPadding)
//        )
//
//        is StockUiState.Error -> ErrorScreen(
//            modifier = modifier.fillMaxWidth()
//        )
//    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(
                dimensionResource(R.dimen.padding_big),
                dimensionResource(R.dimen.padding_medium)
            )
    ) {
        Text(
            text = stringResource(R.string.loading),
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = interFamily,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(
                dimensionResource(R.dimen.padding_big),
                dimensionResource(R.dimen.padding_medium)
            )
    ) {
        Text(
            text = stringResource(R.string.error),
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = interFamily,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun StockCardsScreen(
    stocks: List<Stock>,
    modifier: Modifier = Modifier,
) {
    Scaffold { it ->
        LazyColumn(
            contentPadding = it,
            modifier = modifier
        ) {
            items(stocks) { stock ->
                StockCard(
                    stock = stock,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
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
                                    String.format("$%.2f", stock.priceToday.value)
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
                            } else if (stockGrowth.value < 0) {
                                Text(
                                    text = String.format("%.2f%%", stockGrowth.value),
                                    fontFamily = interFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp,
                                    color = negativeGrowthTextColor
                                )
                            } else {
                                Text(
                                    text = String.format("+%.2f%%", stockGrowth.value),
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

@Preview
@Composable
fun LightStockOfTheDayAppPreview() {
    _30daysappTheme(
        darkTheme = false,
    ) {
        MainScreen()
    }
}

@Preview
@Composable
fun DarkStockOfTheDayAppPreview() {
    _30daysappTheme(
        darkTheme = true,
    ) {
        MainScreen()
    }
}