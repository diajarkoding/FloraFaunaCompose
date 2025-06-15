package com.diajarkoding.florafauna.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = BlueSoft80,
    onPrimary = DarkTextColor,
    secondary = BlueGrey80,
    onSecondary = DarkTextColor,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onBackground = Color.White,
    onSurface = Color.White,
    error = ErrorRed
)

private val LightColorScheme = lightColorScheme(
    primary = BlueSoft40,
    onPrimary = LightTextColor,
    secondary = BlueGrey40,
    onSecondary = LightTextColor,
    background = Color(0xFFF7FBFF),
    surface = Color(0xFFEFF5FA),
    onBackground = LightTextColor,
    onSurface = LightTextColor,
    error = ErrorRed
)

@Composable
fun FloraFaunaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
