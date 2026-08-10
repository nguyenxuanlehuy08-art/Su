package com.example.ui.theme

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
    primary = BronzePrimaryDark,
    secondary = GoldSecondaryDark,
    tertiary = BronzeTertiary,
    background = DarkHistoryBg,
    surface = DarkHistorySurface,
    surfaceVariant = Color(0xFF3B3531),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color(0xFFE7E5E4),
    onSurface = Color(0xFFF5F5F4)
)

private val LightColorScheme = lightColorScheme(
    primary = CrimsonPrimary,
    secondary = GoldSecondary,
    tertiary = BronzeTertiary,
    background = ParchmentLightBg,
    surface = ParchmentSurface,
    surfaceVariant = Color(0xFFF0EAE1),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color(0xFF292524),
    onSurface = Color(0xFF1C1917)
)

@Composable
fun HistoryAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
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
