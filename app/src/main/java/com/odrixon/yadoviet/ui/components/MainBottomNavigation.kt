package com.odrixon.yadoviet.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odrixon.yadoviet.ui.theme.BrandPrimary

@Composable
fun MainBottomNavigation(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    onQrClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Outer container allowing central QR button to protrude naturally at the top
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 22.dp) // Leave space for protruding central QR button
    ) {
        // Bottom White Navigation Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 12.dp,
                    ambientColor = Color.Black.copy(alpha = 0.05f),
                    spotColor = Color.Black.copy(alpha = 0.08f)
                )
                .background(Color.White)
                .border(width = 0.5.dp, color = Color(0xFFF1F5F9))
                .navigationBarsPadding()
                .padding(horizontal = 10.dp)
                .padding(top = 6.dp, bottom = 6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tab 0: Trang chủ
                BottomNavCanvasItem(
                    icon = { isSel -> HomeNavIcon(isSelected = isSel) },
                    label = "Trang chủ",
                    isSelected = selectedTab == 0,
                    onClick = { onTabSelected(0) },
                    modifier = Modifier.weight(1f)
                )

                // Tab 1: Phòng
                BottomNavCanvasItem(
                    icon = { isSel -> RoomNavIcon(isSelected = isSel) },
                    label = "Phòng",
                    isSelected = selectedTab == 1,
                    onClick = { onTabSelected(1) },
                    modifier = Modifier.weight(1f)
                )

                // Central QR spacing placeholder
                Spacer(modifier = Modifier.weight(1f))

                // Tab 2: Hóa đơn
                BottomNavCanvasItem(
                    icon = { isSel -> InvoiceNavIcon(isSelected = isSel) },
                    label = "Hóa đơn",
                    isSelected = selectedTab == 2,
                    onClick = { onTabSelected(2) },
                    modifier = Modifier.weight(1f)
                )

                // Tab 3: Tài khoản
                BottomNavCanvasItem(
                    icon = { isSel -> AccountNavIcon(isSelected = isSel) },
                    label = "Tài khoản",
                    isSelected = selectedTab == 3,
                    onClick = { onTabSelected(3) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Floating Protruding QR Code Button (Aligned to Top Center)
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-20).dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = BrandPrimary.copy(alpha = 0.25f),
                    spotColor = BrandPrimary.copy(alpha = 0.35f)
                )
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(BrandPrimary, Color(0xFF173BAB))
                        )
                    )
                    .clickable { onQrClick() },
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.size(24.dp)) {
                    val w = size.width
                    val h = size.height
                    val scale = w / 24f
                    val stroke = 1.8f * scale

                    // Top Left rect (x=3 y=3 w=7 h=7 rx=1.5)
                    drawRoundRect(color = Color.White, topLeft = Offset(3f * scale, 3f * scale), size = Size(7f * scale, 7f * scale), cornerRadius = CornerRadius(1.5f * scale), style = Stroke(stroke))
                    drawRoundRect(color = Color.White, topLeft = Offset(5.5f * scale, 5.5f * scale), size = Size(2f * scale, 2f * scale), cornerRadius = CornerRadius(0.5f * scale))

                    // Top Right rect (x=14 y=3 w=7 h=7 rx=1.5)
                    drawRoundRect(color = Color.White, topLeft = Offset(14f * scale, 3f * scale), size = Size(7f * scale, 7f * scale), cornerRadius = CornerRadius(1.5f * scale), style = Stroke(stroke))
                    drawRoundRect(color = Color.White, topLeft = Offset(16.5f * scale, 5.5f * scale), size = Size(2f * scale, 2f * scale), cornerRadius = CornerRadius(0.5f * scale))

                    // Bottom Left rect (x=3 y=14 w=7 h=7 rx=1.5)
                    drawRoundRect(color = Color.White, topLeft = Offset(3f * scale, 14f * scale), size = Size(7f * scale, 7f * scale), cornerRadius = CornerRadius(1.5f * scale), style = Stroke(stroke))
                    drawRoundRect(color = Color.White, topLeft = Offset(5.5f * scale, 16.5f * scale), size = Size(2f * scale, 2f * scale), cornerRadius = CornerRadius(0.5f * scale))

                    // Data blocks
                    drawRect(color = Color.White, topLeft = Offset(14f * scale, 14f * scale), size = Size(2.5f * scale, 2.5f * scale))
                    drawRect(color = Color.White, topLeft = Offset(18.5f * scale, 14f * scale), size = Size(2.5f * scale, 2.5f * scale))
                    drawRect(color = Color.White, topLeft = Offset(14f * scale, 18.5f * scale), size = Size(2.5f * scale, 2.5f * scale))
                    drawRect(color = Color.White, topLeft = Offset(18.5f * scale, 18.5f * scale), size = Size(2.5f * scale, 2.5f * scale))
                }
            }
        }
    }
}

@Composable
private fun BottomNavCanvasItem(
    icon: @Composable (Boolean) -> Unit,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.size(22.dp), contentAlignment = Alignment.Center) {
            icon(isSelected)
        }
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = label,
            fontSize = 10.5.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) BrandPrimary else Color(0xFF64748B)
        )
    }
}

// 1. Home Solid/Filled Icon
@Composable
private fun HomeNavIcon(isSelected: Boolean, modifier: Modifier = Modifier.size(22.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val scale = w / 24f
        val color = if (isSelected) BrandPrimary else Color(0xFF94A3B8)

        val path = Path().apply {
            moveTo(11.26f * scale, 2.45f * scale)
            lineTo(3.26f * scale, 8.67f * scale)
            quadraticBezierTo(2.5f * scale, 9.28f * scale, 2.5f * scale, 10.22f * scale)
            lineTo(2.5f * scale, 19.5f * scale)
            quadraticBezierTo(2.5f * scale, 21f * scale, 4f * scale, 21f * scale)
            lineTo(9f * scale, 21f * scale)
            lineTo(9f * scale, 14.5f * scale)
            lineTo(15f * scale, 14.5f * scale)
            lineTo(15f * scale, 21f * scale)
            lineTo(20f * scale, 21f * scale)
            quadraticBezierTo(21.5f * scale, 21f * scale, 21.5f * scale, 19.5f * scale)
            lineTo(21.5f * scale, 10.22f * scale)
            quadraticBezierTo(21.5f * scale, 9.28f * scale, 20.74f * scale, 8.67f * scale)
            lineTo(12.74f * scale, 2.45f * scale)
            close()
        }
        drawPath(path = path, color = color)
    }
}

// 2. Room Building Outline Icon
@Composable
private fun RoomNavIcon(isSelected: Boolean, modifier: Modifier = Modifier.size(22.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val scale = w / 24f
        val color = if (isSelected) BrandPrimary else Color(0xFF94A3B8)

        val roof = Path().apply {
            moveTo(3f * scale, 10.5f * scale)
            lineTo(12f * scale, 3.5f * scale)
            lineTo(21f * scale, 10.5f * scale)
            lineTo(21f * scale, 20f * scale)
            quadraticBezierTo(21f * scale, 21f * scale, 20f * scale, 21f * scale)
            lineTo(4f * scale, 21f * scale)
            quadraticBezierTo(3f * scale, 21f * scale, 3f * scale, 20f * scale)
            close()
        }
        drawPath(path = roof, color = color, style = Stroke(width = 1.8f * scale, cap = StrokeCap.Round, join = StrokeJoin.Round))

        val door = Path().apply {
            moveTo(9.5f * scale, 21f * scale)
            lineTo(9.5f * scale, 13.5f * scale)
            lineTo(14.5f * scale, 13.5f * scale)
            lineTo(14.5f * scale, 21f * scale)
        }
        drawPath(path = door, color = color, style = Stroke(width = 1.8f * scale, cap = StrokeCap.Round, join = StrokeJoin.Round))
    }
}

// 3. Invoice Receipt Outline Icon
@Composable
private fun InvoiceNavIcon(isSelected: Boolean, modifier: Modifier = Modifier.size(22.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val scale = w / 24f
        val color = if (isSelected) BrandPrimary else Color(0xFF94A3B8)

        val receipt = Path().apply {
            moveTo(4f * scale, 2f * scale)
            lineTo(4f * scale, 22f * scale)
            lineTo(7f * scale, 20f * scale)
            lineTo(10f * scale, 22f * scale)
            lineTo(13f * scale, 20f * scale)
            lineTo(16f * scale, 22f * scale)
            lineTo(19f * scale, 20f * scale)
            lineTo(20f * scale, 22f * scale)
            lineTo(20f * scale, 2f * scale)
            lineTo(17f * scale, 4f * scale)
            lineTo(14f * scale, 2f * scale)
            lineTo(11f * scale, 4f * scale)
            lineTo(8f * scale, 2f * scale)
            close()
        }
        drawPath(path = receipt, color = color, style = Stroke(width = 1.8f * scale, cap = StrokeCap.Round, join = StrokeJoin.Round))

        drawLine(color = color, start = Offset(8f * scale, 8f * scale), end = Offset(16f * scale, 8f * scale), strokeWidth = 1.8f * scale, cap = StrokeCap.Round)
        drawLine(color = color, start = Offset(8f * scale, 12f * scale), end = Offset(16f * scale, 12f * scale), strokeWidth = 1.8f * scale, cap = StrokeCap.Round)
        drawLine(color = color, start = Offset(8f * scale, 16f * scale), end = Offset(13f * scale, 16f * scale), strokeWidth = 1.8f * scale, cap = StrokeCap.Round)
    }
}

// 4. Account Person Outline Icon
@Composable
private fun AccountNavIcon(isSelected: Boolean, modifier: Modifier = Modifier.size(22.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val scale = w / 24f
        val color = if (isSelected) BrandPrimary else Color(0xFF94A3B8)

        drawCircle(color = color, radius = 4f * scale, center = Offset(12f * scale, 7.5f * scale), style = Stroke(width = 1.8f * scale))

        val body = Path().apply {
            moveTo(4.5f * scale, 20.5f * scale)
            cubicTo(4.5f * scale, 16.9f * scale, 7.9f * scale, 14f * scale, 12f * scale, 14f * scale)
            cubicTo(16.1f * scale, 14f * scale, 19.5f * scale, 16.9f * scale, 19.5f * scale, 20.5f * scale)
        }
        drawPath(path = body, color = color, style = Stroke(width = 1.8f * scale, cap = StrokeCap.Round))
    }
}
