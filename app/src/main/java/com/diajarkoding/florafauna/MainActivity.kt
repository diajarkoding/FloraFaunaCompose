package com.diajarkoding.florafauna

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.diajarkoding.florafauna.ui.MainScreen
import com.diajarkoding.florafauna.ui.theme.FloraFaunaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FloraFaunaTheme {
                MainScreen()
            }
        }
    }
}
