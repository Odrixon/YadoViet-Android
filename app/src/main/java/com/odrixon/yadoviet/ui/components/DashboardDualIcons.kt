package com.odrixon.yadoviet.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

val ColorNavy = Color(0xFF032C95)
val ColorEmerald = Color(0xFF00B14F)

@Composable
fun BranchDualIcon(modifier: Modifier = Modifier.size(24.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val scale = w / 24f

        // Subtle drop shadow behind the icon
        drawCircle(
            color = ColorNavy.copy(alpha = 0.06f),
            radius = 10f * scale,
            center = Offset(12f * scale, 13f * scale)
        )

        // Ground line: M3 21H21
        drawLine(
            color = ColorNavy,
            start = Offset(3f * scale, 21f * scale),
            end = Offset(21f * scale, 21f * scale),
            strokeWidth = 1.8f * scale,
            cap = StrokeCap.Round
        )

        // Main building
        val mainBuilding = Path().apply {
            moveTo(5f * scale, 21f * scale)
            lineTo(5f * scale, 5f * scale)
            quadraticBezierTo(5f * scale, 3f * scale, 7f * scale, 3f * scale)
            lineTo(13f * scale, 3f * scale)
            quadraticBezierTo(15f * scale, 3f * scale, 15f * scale, 5f * scale)
            lineTo(15f * scale, 21f * scale)
        }
        drawPath(
            path = mainBuilding,
            color = ColorNavy,
            style = Stroke(width = 1.8f * scale)
        )

        // Side building
        val sideBuilding = Path().apply {
            moveTo(15f * scale, 10f * scale)
            lineTo(19f * scale, 10f * scale)
            quadraticBezierTo(20f * scale, 10f * scale, 20f * scale, 11f * scale)
            lineTo(20f * scale, 21f * scale)
        }
        drawPath(
            path = sideBuilding,
            color = ColorNavy,
            style = Stroke(width = 1.8f * scale)
        )

        // Emerald Windows
        val windows = listOf(
            Offset(8f * scale, 6f * scale),
            Offset(11f * scale, 6f * scale),
            Offset(8f * scale, 10f * scale),
            Offset(11f * scale, 10f * scale),
            Offset(8f * scale, 14f * scale),
            Offset(11f * scale, 14f * scale)
        )
        val winSize = Size(2f * scale, 2f * scale)
        val winRadius = CornerRadius(0.5f * scale, 0.5f * scale)
        windows.forEach {
            drawRoundRect(
                color = ColorEmerald,
                topLeft = it,
                size = winSize,
                cornerRadius = winRadius
            )
        }
    }
}

@Composable
fun ContractDualIcon(modifier: Modifier = Modifier.size(24.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val scale = w / 24f

        drawCircle(
            color = ColorNavy.copy(alpha = 0.06f),
            radius = 10f * scale,
            center = Offset(12f * scale, 13f * scale)
        )

        // Document outline
        val doc = Path().apply {
            moveTo(14f * scale, 2f * scale)
            lineTo(6f * scale, 2f * scale)
            quadraticBezierTo(4f * scale, 2f * scale, 4f * scale, 4f * scale)
            lineTo(4f * scale, 20f * scale)
            quadraticBezierTo(4f * scale, 22f * scale, 6f * scale, 22f * scale)
            lineTo(18f * scale, 22f * scale)
            quadraticBezierTo(20f * scale, 22f * scale, 20f * scale, 20f * scale)
            lineTo(20f * scale, 8f * scale)
            close()
        }
        drawPath(path = doc, color = ColorNavy, style = Stroke(width = 1.8f * scale, cap = StrokeCap.Round, join = StrokeJoin.Round))

        // Fold line
        val fold = Path().apply {
            moveTo(14f * scale, 2f * scale)
            lineTo(14f * scale, 8f * scale)
            lineTo(20f * scale, 8f * scale)
        }
        drawPath(path = fold, color = ColorNavy, style = Stroke(width = 1.8f * scale, cap = StrokeCap.Round, join = StrokeJoin.Round))

        // Emerald lines
        drawLine(color = ColorEmerald, start = Offset(8f * scale, 13f * scale), end = Offset(14f * scale, 13f * scale), strokeWidth = 1.6f * scale, cap = StrokeCap.Round)
        drawLine(color = ColorEmerald, start = Offset(8f * scale, 17f * scale), end = Offset(12f * scale, 17f * scale), strokeWidth = 1.6f * scale, cap = StrokeCap.Round)

        // Emerald signature loop
        val sig = Path().apply {
            moveTo(15f * scale, 16.5f * scale)
            quadraticBezierTo(16.5f * scale, 15.5f * scale, 17.5f * scale, 16.5f * scale)
            quadraticBezierTo(18f * scale, 17.5f * scale, 16f * scale, 19f * scale)
        }
        drawPath(path = sig, color = ColorEmerald, style = Stroke(width = 1.4f * scale, cap = StrokeCap.Round))
    }
}

@Composable
fun BankAccountDualIcon(modifier: Modifier = Modifier.size(24.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val scale = w / 24f

        drawCircle(
            color = ColorNavy.copy(alpha = 0.06f),
            radius = 10f * scale,
            center = Offset(12f * scale, 13f * scale)
        )

        // Card rect
        drawRoundRect(
            color = ColorNavy,
            topLeft = Offset(3f * scale, 5f * scale),
            size = Size(18f * scale, 14f * scale),
            cornerRadius = CornerRadius(2.5f * scale, 2.5f * scale),
            style = Stroke(width = 1.8f * scale)
        )
        // Black magnetic bar
        drawLine(
            color = ColorNavy,
            start = Offset(3f * scale, 10f * scale),
            end = Offset(21f * scale, 10f * scale),
            strokeWidth = 1.6f * scale
        )
        // Emerald chip
        drawRoundRect(
            color = ColorEmerald,
            topLeft = Offset(6f * scale, 13.5f * scale),
            size = Size(3.5f * scale, 2.5f * scale),
            cornerRadius = CornerRadius(0.6f * scale, 0.6f * scale)
        )
        // Emerald contactless dots
        drawCircle(color = ColorEmerald, radius = 1f * scale, center = Offset(14f * scale, 14.75f * scale))
        drawCircle(color = ColorEmerald, radius = 1f * scale, center = Offset(17f * scale, 14.75f * scale))
    }
}

@Composable
fun UtilityDualIcon(modifier: Modifier = Modifier.size(24.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val scale = w / 24f

        drawCircle(
            color = ColorNavy.copy(alpha = 0.06f),
            radius = 10f * scale,
            center = Offset(12f * scale, 13f * scale)
        )

        // Water drop Navy
        val drop = Path().apply {
            moveTo(12f * scale, 2.5f * scale)
            cubicTo(12f * scale, 2.5f * scale, 5.5f * scale, 10f * scale, 5.5f * scale, 15f * scale)
            cubicTo(5.5f * scale, 18.6f * scale, 8.4f * scale, 21.5f * scale, 12f * scale, 21.5f * scale)
            cubicTo(15.6f * scale, 21.5f * scale, 18.5f * scale, 18.6f * scale, 18.5f * scale, 15f * scale)
            cubicTo(18.5f * scale, 10f * scale, 12f * scale, 2.5f * scale, 12f * scale, 2.5f * scale)
            close()
        }
        drawPath(path = drop, color = ColorNavy, style = Stroke(width = 1.8f * scale, cap = StrokeCap.Round, join = StrokeJoin.Round))

        // Lightning bolt Emerald
        val bolt = Path().apply {
            moveTo(12.5f * scale, 9f * scale)
            lineTo(10f * scale, 13.5f * scale)
            lineTo(13.5f * scale, 13.5f * scale)
            lineTo(11.5f * scale, 18f * scale)
        }
        drawPath(path = bolt, color = ColorEmerald, style = Stroke(width = 1.8f * scale, cap = StrokeCap.Round, join = StrokeJoin.Round))
    }
}

@Composable
fun TenantsDualIcon(modifier: Modifier = Modifier.size(24.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val scale = w / 24f

        drawCircle(
            color = ColorNavy.copy(alpha = 0.06f),
            radius = 10f * scale,
            center = Offset(12f * scale, 13f * scale)
        )

        drawCircle(color = ColorNavy, radius = 3.5f * scale, center = Offset(10f * scale, 8f * scale), style = Stroke(width = 1.8f * scale))
        val body = Path().apply {
            moveTo(3.5f * scale, 19f * scale)
            cubicTo(3.5f * scale, 15.5f * scale, 6.4f * scale, 13.5f * scale, 10f * scale, 13.5f * scale)
            cubicTo(13.6f * scale, 13.5f * scale, 16.5f * scale, 15.5f * scale, 16.5f * scale, 19f * scale)
        }
        drawPath(path = body, color = ColorNavy, style = Stroke(width = 1.8f * scale, cap = StrokeCap.Round))

        // Shield Emerald
        val shield = Path().apply {
            moveTo(16f * scale, 11f * scale)
            lineTo(19f * scale, 12.5f * scale)
            lineTo(19f * scale, 15.5f * scale)
            cubicTo(19f * scale, 17.5f * scale, 17.5f * scale, 19f * scale, 16f * scale, 19.5f * scale)
            cubicTo(14.5f * scale, 19f * scale, 13f * scale, 17.5f * scale, 13f * scale, 15.5f * scale)
            lineTo(13f * scale, 12.5f * scale)
            close()
        }
        drawPath(path = shield, color = ColorEmerald, style = Stroke(width = 1.5f * scale, cap = StrokeCap.Round, join = StrokeJoin.Round))

        // Checkmark inside shield
        val check = Path().apply {
            moveTo(15f * scale, 15.2f * scale)
            lineTo(16f * scale, 16.2f * scale)
            lineTo(17.5f * scale, 14.5f * scale)
        }
        drawPath(path = check, color = ColorEmerald, style = Stroke(width = 1.3f * scale, cap = StrokeCap.Round, join = StrokeJoin.Round))
    }
}

@Composable
fun AccountingDualIcon(modifier: Modifier = Modifier.size(24.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val scale = w / 24f

        drawCircle(
            color = ColorNavy.copy(alpha = 0.06f),
            radius = 10f * scale,
            center = Offset(12f * scale, 13f * scale)
        )

        // Wallet box
        val wallet = Path().apply {
            moveTo(4f * scale, 11.5f * scale)
            lineTo(6f * scale, 9.5f * scale)
            lineTo(18f * scale, 9.5f * scale)
            lineTo(20f * scale, 11.5f * scale)
            lineTo(20f * scale, 19f * scale)
            lineTo(18f * scale, 21f * scale)
            lineTo(6f * scale, 21f * scale)
            lineTo(4f * scale, 19f * scale)
            close()
        }
        drawPath(path = wallet, color = ColorNavy, style = Stroke(width = 1.8f * scale))

        // Flap Navy
        val flap = Path().apply {
            moveTo(15f * scale, 14f * scale)
            lineTo(18.5f * scale, 14f * scale)
            lineTo(20f * scale, 15.5f * scale)
            lineTo(18.5f * scale, 17f * scale)
            lineTo(15f * scale, 17f * scale)
            close()
        }
        drawPath(path = flap, color = ColorNavy, style = Stroke(width = 1.6f * scale))

        // Coin Emerald
        drawCircle(color = ColorEmerald, radius = 3.2f * scale, center = Offset(12f * scale, 5f * scale), style = Stroke(width = 1.6f * scale))
        drawLine(color = ColorEmerald, start = Offset(12f * scale, 3.5f * scale), end = Offset(12f * scale, 6.5f * scale), strokeWidth = 1.3f * scale, cap = StrokeCap.Round)
        drawLine(color = ColorEmerald, start = Offset(10.8f * scale, 5f * scale), end = Offset(13.2f * scale, 5f * scale), strokeWidth = 1.3f * scale, cap = StrokeCap.Round)
    }
}

@Composable
fun ReportsDualIcon(modifier: Modifier = Modifier.size(24.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val scale = w / 24f

        drawCircle(
            color = ColorNavy.copy(alpha = 0.06f),
            radius = 10f * scale,
            center = Offset(12f * scale, 13f * scale)
        )

        // Base line
        drawLine(color = ColorNavy, start = Offset(3.5f * scale, 20.5f * scale), end = Offset(20.5f * scale, 20.5f * scale), strokeWidth = 1.8f * scale, cap = StrokeCap.Round)

        // 3 Bar charts Navy
        drawLine(color = ColorNavy, start = Offset(7f * scale, 20.5f * scale), end = Offset(7f * scale, 14f * scale), strokeWidth = 2.5f * scale, cap = StrokeCap.Round)
        drawLine(color = ColorNavy, start = Offset(12f * scale, 20.5f * scale), end = Offset(12f * scale, 10f * scale), strokeWidth = 2.5f * scale, cap = StrokeCap.Round)
        drawLine(color = ColorNavy, start = Offset(17f * scale, 20.5f * scale), end = Offset(17f * scale, 6f * scale), strokeWidth = 2.5f * scale, cap = StrokeCap.Round)

        // Arrow Emerald
        val arrowHead = Path().apply {
            moveTo(15f * scale, 3.5f * scale)
            lineTo(20.5f * scale, 3.5f * scale)
            lineTo(20.5f * scale, 9f * scale)
        }
        drawPath(path = arrowHead, color = ColorEmerald, style = Stroke(width = 1.8f * scale, cap = StrokeCap.Round, join = StrokeJoin.Round))
        drawLine(color = ColorEmerald, start = Offset(20.5f * scale, 3.5f * scale), end = Offset(14f * scale, 10f * scale), strokeWidth = 1.8f * scale, cap = StrokeCap.Round)
    }
}

@Composable
fun MoreDualIcon(modifier: Modifier = Modifier.size(24.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val scale = w / 24f

        drawCircle(
            color = ColorNavy.copy(alpha = 0.06f),
            radius = 10f * scale,
            center = Offset(12f * scale, 13f * scale)
        )

        // 4 dots pattern
        drawCircle(color = ColorNavy, radius = 1.6f * scale, center = Offset(9f * scale, 9f * scale))
        drawCircle(color = ColorEmerald, radius = 1.6f * scale, center = Offset(15f * scale, 9f * scale))
        drawCircle(color = ColorEmerald, radius = 1.6f * scale, center = Offset(9f * scale, 15f * scale))
        drawCircle(color = ColorNavy, radius = 1.6f * scale, center = Offset(15f * scale, 15f * scale))
    }
}
