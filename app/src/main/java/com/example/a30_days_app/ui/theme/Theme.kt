package com.example.a30_days_app.ui.theme

import android.app.Activity
import android.hardware.lights.Light
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = dark_theme_primary,
    onPrimary = dark_theme_onPrimary,
    primaryContainer = Color.Black,
    onPrimaryContainer = Color.White,
    secondary = dark_theme_secondary,
    onSecondary = dark_theme_onSecondary,
    secondaryContainer = Color.Black,
    onSecondaryContainer = Color.White,
    tertiary = dark_theme_tertiary,
    onTertiary = Color.White,
    tertiaryContainer = Color.Black,
    onTertiaryContainer = Color.White,
    error = Color.Red,
    errorContainer = Color.Black,
    onError = Color.Red,
    onErrorContainer = Color.Red,
    background = dark_theme_background,
    onBackground = Color.Black,
    surface = Color.Black,
    onSurface = Color.White,
    surfaceVariant = Color.Black,
    onSurfaceVariant = Color.White,
    outline = Color.Black,
    inverseOnSurface = Color.Black,
    inverseSurface = Color.Black,
    inversePrimary = Color.Black,
    surfaceTint = Color.Black,
    outlineVariant = Color.Black,
    scrim = Color.Black,
)

private val LightColorScheme = lightColorScheme(
    primary = light_theme_primary,
    onPrimary = light_theme_onPrimary,
    primaryContainer = Color.White,
    onPrimaryContainer = Color.Black,
    secondary = light_theme_secondary,
    onSecondary = light_theme_onSecondary,
    secondaryContainer = Color.White,
    onSecondaryContainer = Color.Black,
    tertiary = light_theme_tertiary,
    onTertiary = Color.Black,
    tertiaryContainer = Color.Black,
    onTertiaryContainer = Color.White,
    error = Color.Red,
    errorContainer = Color.White,
    onError = Color.Red,
    onErrorContainer = Color.Red,
    background = light_theme_background,
    onBackground = Color.White,
    surface = Color.White,
    onSurface = Color.Black,
    surfaceVariant = Color.White,
    onSurfaceVariant = Color.Black,
    outline = Color.White,
    inverseOnSurface = Color.White,
    inverseSurface = Color.White,
    inversePrimary = Color.White,
    surfaceTint = Color.White,
    outlineVariant = Color.White,
    scrim = Color.White,
)

@Composable
fun _30daysappTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
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