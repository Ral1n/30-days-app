@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.a30_days_app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a30_days_app.R
import com.example.a30_days_app.ui.screens.MainScreen
import com.example.a30_days_app.ui.theme.interFamily
import com.example.a30_days_app.viewmodel.StockViewModel

@Composable
fun StockOfTheDayApp(modifier: Modifier = Modifier) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier,
        topBar = {
            StockTopAppBar(scrollBehavior = scrollBehavior)
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val stockViewModel: StockViewModel =
                viewModel(factory = StockViewModel.Factory)

            MainScreen(
                viewModel = stockViewModel,
                contentPadding = it
            )
        }
    }
}


@Composable
fun StockTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        CenterAlignedTopAppBar(
            scrollBehavior = scrollBehavior,
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