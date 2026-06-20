package com.odrixon.yadoviet.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Primary = Color(0xFF004BCA)
val PrimaryVariant = Color(0xFF003EA8)
val Background = Color(0xFFF7FAFD)
val Surface = Color(0xFFFFFFFF)
val SurfaceContainerLow = Color(0xFFF1F4F7)
val OnSurface = Color(0xFF181C1E)
val OnSurfaceVariant = Color(0xFF424656)
val Outline = Color(0xFF737687)
val OutlineVariant = Color(0xFFC2C6D9)
val ZaloBlue = Color(0xFF0068FF)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = Color.White,
    background = Background,
    onBackground = OnSurface,
    surface = Surface,
    onSurface = OnSurface,
    surfaceVariant = SurfaceContainerLow,
    onSurfaceVariant = OnSurfaceVariant,
    outline = Outline,
    outlineVariant = OutlineVariant
)

@Composable
fun YadoVietTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme // Keep light theme for this app per spec

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
