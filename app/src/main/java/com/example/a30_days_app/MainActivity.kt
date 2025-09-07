package com.example.a30_days_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.example.a30_days_app.ui.theme._30daysappTheme
import androidx.compose.material3.Surface
import com.example.a30_days_app.ui.StockOfTheDayApp

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