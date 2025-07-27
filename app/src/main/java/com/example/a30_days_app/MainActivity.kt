package com.example.a30_days_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30_days_app.model.Stock
import com.example.a30_days_app.ui.theme._30daysappTheme
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _30daysappTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    StockCard(
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
            }
        }
    }
}

@Composable
fun StockCard(stock: Stock, modifier: Modifier = Modifier) {
    Card (
        modifier = Modifier
    ){
        Text(
            text = stringResource(stock.titleId),
            style = MaterialTheme.typography.headlineLarge
        )

        Image(
            painter = painterResource(stock.imageResourceId),
            contentDescription = null,
            modifier = Modifier.size(width = 300.dp, height = 300.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StockCardPreview() {
    _30daysappTheme {
        StockCard(Stock(titleId = R.string.stock_title1, imageResourceId = R.drawable.google, descriptionId = R.string.stock_description1))
    }
}