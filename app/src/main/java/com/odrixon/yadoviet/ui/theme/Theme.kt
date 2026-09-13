package com.odrixon.yadoviet.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Brand Colors
val BrandPrimary = Color(0xFF032C95)
val BrandPrimaryDark = Color(0xFF0040A8)
val BrandPrimaryLight = Color(0xFF1E3E8C)
val BrandAccent = Color(0xFF3B82F6)

// Base UI Colors
val AppBackground = Color(0xFFFFFFFF)
val CardBackground = Color(0xFFFFFFFF)
val InputBackground = Color(0xFFF8FAFC)
val InputBorder = Color(0xFFE2E8F0)
val InputBorderFocus = Color(0xFF032C95)

val TextPrimary = Color(0xFF0F172A)
val TextSecondary = Color(0xFF64748B)
val TextMuted = Color(0xFF94A3B8)

val DividerColor = Color(0xFFE2E8F0)
val GoogleBorderColor = Color(0xFFBFDBFE)

// Backward compatibility tokens
val Primary = BrandPrimary
val PrimaryVariant = BrandPrimaryDark
val Background = AppBackground
val Surface = CardBackground
val SurfaceContainerLow = InputBackground
val OnSurface = TextPrimary
val OnSurfaceVariant = TextSecondary
val Outline = TextMuted
val OutlineVariant = InputBorder

private val LightColorScheme = lightColorScheme(
    primary = BrandPrimary,
    onPrimary = Color.White,
    background = AppBackground,
    onBackground = TextPrimary,
    surface = CardBackground,
    onSurface = TextPrimary,
    surfaceVariant = InputBackground,
    onSurfaceVariant = TextSecondary,
    outline = TextMuted,
    outlineVariant = InputBorder
)

@Composable
fun YadoVietTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                // Set default status bar and navigation bar to white with dark icons
                window.statusBarColor = android.graphics.Color.WHITE
                window.navigationBarColor = android.graphics.Color.WHITE
                val insetsController = WindowCompat.getInsetsController(window, view)
                insetsController.isAppearanceLightStatusBars = true
                insetsController.isAppearanceLightNavigationBars = true
            }
        }
    }

    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}
