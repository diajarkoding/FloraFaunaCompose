package com.diajarkoding.florafauna

import SpeciesPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.diajarkoding.florafauna.ui.MainScreen
import com.diajarkoding.florafauna.ui.theme.FloraFaunaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = SpeciesPreferences(applicationContext)

        enableEdgeToEdge()
        setContent {
            FloraFaunaTheme {
                MainScreen(speciesPreferences = preferences)
            }
        }
    }
}
