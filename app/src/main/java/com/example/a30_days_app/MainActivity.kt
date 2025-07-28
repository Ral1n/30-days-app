package com.example.a30_days_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a30_days_app.model.Stock
import com.example.a30_days_app.ui.theme.BebasNeue
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
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier.size(width = 500.dp, height = 400.dp),
    ){
        Text(
            text = stringResource(stock.titleId),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )

        Row {
            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(stock.imageResourceId),
                contentDescription = null,
                modifier = Modifier.size(width = 200.dp, height = 200.dp)
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            OutlinedCard(
                border = BorderStroke(1.dp, Color.DarkGray),
                modifier = Modifier.size(96.dp, 96.dp),

            ) {
                Text(
                    text = "$999.99",
                    fontFamily = BebasNeue,
                    fontSize = 32.sp,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            OutlinedCard(
                border = BorderStroke(1.dp, Color.DarkGray),
                modifier = Modifier.size(96.dp, 96.dp)
            ) {
                Text(
                    text = "$999.99",
                    fontFamily = BebasNeue,
                    fontSize = 32.sp,
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }


        Text(
            text = stringResource(stock.descriptionId),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
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