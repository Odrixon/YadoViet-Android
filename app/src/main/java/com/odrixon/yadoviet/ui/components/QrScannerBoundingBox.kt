package com.odrixon.yadoviet.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun QrScannerBoundingBox(
    boxSize: Dp = 260.dp,
    cornerLength: Dp = 36.dp,
    cornerStrokeWidth: Dp = 4.5.dp,
    cornerRadius: Dp = 22.dp,
    boxColor: Color = Color.White,
    overlayScrimColor: Color = Color.Black.copy(alpha = 0.50f),
    modifier: Modifier = Modifier
) {
    // Laser scan animation line moving up and down
    val infiniteTransition = rememberInfiniteTransition(label = "LaserScanTransition")
    val laserProgress by infiniteTransition.animateFloat(
        initialValue = 0.05f,
        targetValue = 0.95f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "LaserProgress"
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        val boxWidthPx = boxSize.toPx()
        val left = (width - boxWidthPx) / 2f
        val top = (height - boxWidthPx) / 2f
        val right = left + boxWidthPx
        val bottom = top + boxWidthPx

        val cLength = cornerLength.toPx()
        val cRadius = cornerRadius.toPx()
        val stroke = cornerStrokeWidth.toPx()

        // 1. Semi-transparent dark overlay with rounded transparent cutout window
        val outerPath = Path().apply {
            addRect(Rect(0f, 0f, width, height))
        }
        val innerPath = Path().apply {
            addRoundRect(
                RoundRect(
                    left = left,
                    top = top,
                    right = right,
                    bottom = bottom,
                    radiusX = cRadius,
                    radiusY = cRadius
                )
            )
        }
        val scrimPath = Path.combine(
            operation = PathOperation.Difference,
            path1 = outerPath,
            path2 = innerPath
        )
        drawPath(path = scrimPath, color = overlayScrimColor)

        // 2. Subtle thin guide frame around cutout
        drawRoundRect(
            color = Color.White.copy(alpha = 0.20f),
            topLeft = Offset(left, top),
            size = Size(boxWidthPx, boxWidthPx),
            cornerRadius = CornerRadius(cRadius, cRadius),
            style = Stroke(width = 1.dp.toPx())
        )

        // 3. Four Bold White Rounded Corner Brackets (Đúng chuẩn thiết kế trong ảnh mẫu)
        
        // --- TOP-LEFT CORNER ---
        val topLeftPath = Path().apply {
            moveTo(left, top + cLength)
            lineTo(left, top + cRadius)
            quadraticBezierTo(left, top, left + cRadius, top)
            lineTo(left + cLength, top)
        }
        drawPath(
            path = topLeftPath,
            color = boxColor,
            style = Stroke(width = stroke, cap = StrokeCap.Round)
        )

        // --- TOP-RIGHT CORNER ---
        val topRightPath = Path().apply {
            moveTo(right - cLength, top)
            lineTo(right - cRadius, top)
            quadraticBezierTo(right, top, right, top + cRadius)
            lineTo(right, top + cLength)
        }
        drawPath(
            path = topRightPath,
            color = boxColor,
            style = Stroke(width = stroke, cap = StrokeCap.Round)
        )

        // --- BOTTOM-LEFT CORNER ---
        val bottomLeftPath = Path().apply {
            moveTo(left, bottom - cLength)
            lineTo(left, bottom - cRadius)
            quadraticBezierTo(left, bottom, left + cRadius, bottom)
            lineTo(left + cLength, bottom)
        }
        drawPath(
            path = bottomLeftPath,
            color = boxColor,
            style = Stroke(width = stroke, cap = StrokeCap.Round)
        )

        // --- BOTTOM-RIGHT CORNER ---
        val bottomRightPath = Path().apply {
            moveTo(right - cLength, bottom)
            lineTo(right - cRadius, bottom)
            quadraticBezierTo(right, bottom, right, bottom - cRadius)
            lineTo(right, bottom - cLength)
        }
        drawPath(
            path = bottomRightPath,
            color = boxColor,
            style = Stroke(width = stroke, cap = StrokeCap.Round)
        )

        // 4. Futuristic Laser Scanning Beam Line with Gradient Glow
        val laserY = top + (boxWidthPx * laserProgress)
        val laserMargin = 12.dp.toPx()
        drawLine(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    Color.Transparent,
                    Color(0xFF38BDF8).copy(alpha = 0.4f),
                    Color(0xFF00E676),
                    Color(0xFF38BDF8).copy(alpha = 0.4f),
                    Color.Transparent
                ),
                startX = left + laserMargin,
                endX = right - laserMargin
            ),
            start = Offset(left + laserMargin, laserY),
            end = Offset(right - laserMargin, laserY),
            strokeWidth = 2.5.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}
