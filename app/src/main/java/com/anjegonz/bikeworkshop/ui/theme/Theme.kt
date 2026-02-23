package com.anjegonz.bikeworkshop.ui.theme

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
    primary = GarageOrange,
    onPrimary = Color.White,
    primaryContainer = GarageOrangeDark,
    onPrimaryContainer = Color.White,

    secondary = GarageOrangeLight,
    onSecondary = OnLight,
    secondaryContainer = SurfaceDark3,
    onSecondaryContainer = OnDark,

    background = SurfaceDark,
    onBackground = OnDark,

    surface = SurfaceDark,
    onSurface = OnDark,
    surfaceVariant = SurfaceDark2,
    onSurfaceVariant = OnDark,

    outline = SurfaceDark3
)

private val LightColorScheme = lightColorScheme(
    primary = GarageOrange,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFCCBC),
    onPrimaryContainer = GarageOrangeDark,

    secondary = GarageOrangeDark,
    onSecondary = Color.White,
    secondaryContainer = SurfaceLight3,
    onSecondaryContainer = OnLight,

    background = SurfaceLight,
    onBackground = OnLight,

    surface = SurfaceLight,
    onSurface = OnLight,
    surfaceVariant = SurfaceLight2,
    onSurfaceVariant = OnLight,

    outline = SurfaceLight3
)

@Composable
fun BikeWorkshopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
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