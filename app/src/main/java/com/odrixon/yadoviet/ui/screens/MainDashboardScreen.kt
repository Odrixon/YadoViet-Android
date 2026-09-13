package com.odrixon.yadoviet.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odrixon.yadoviet.R
import com.odrixon.yadoviet.ui.components.AccountingDualIcon
import com.odrixon.yadoviet.ui.components.BankAccountDualIcon
import com.odrixon.yadoviet.ui.components.BranchDualIcon
import com.odrixon.yadoviet.ui.components.ContractDualIcon
import com.odrixon.yadoviet.ui.components.MainBottomNavigation
import com.odrixon.yadoviet.ui.components.MoreDualIcon
import com.odrixon.yadoviet.ui.components.ReportsDualIcon
import com.odrixon.yadoviet.ui.components.TenantsDualIcon
import com.odrixon.yadoviet.ui.components.UtilityDualIcon
import com.odrixon.yadoviet.ui.theme.BrandPrimary
import com.odrixon.yadoviet.ui.theme.TextMuted
import com.odrixon.yadoviet.ui.theme.TextPrimary
import com.odrixon.yadoviet.ui.theme.TextSecondary

@Composable
fun MainDashboardScreen(
    onNavigateToRooms: () -> Unit = {},
    onNavigateToInvoices: () -> Unit = {},
    onNavigateToAccount: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            MainBottomNavigation(
                selectedTab = selectedTab,
                onTabSelected = { tabIndex ->
                    selectedTab = tabIndex
                    when (tabIndex) {
                        1 -> onNavigateToRooms()
                        2 -> onNavigateToInvoices()
                        3 -> onNavigateToAccount()
                    }
                },
                onQrClick = {
                    Toast.makeText(context, "Mở trình quét mã QR", Toast.LENGTH_SHORT).show()
                }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8FAFC))
                .verticalScroll(rememberScrollState())
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            // ==================== 1. TOP BANNER WALLPAPER ====================
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Color(0xFF0F172A))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.header_wallpaper),
                    contentDescription = "Header Wallpaper",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // ==================== 2. MAIN CONTENT ====================
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            ) {
                // Header Greeting + Search & Notification Icons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Xin chào, Người dùng 👋",
                        color = TextPrimary,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-0.5).sp
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        // Search Button
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .shadow(
                                    elevation = 4.dp,
                                    shape = CircleShape,
                                    ambientColor = Color.Black.copy(alpha = 0.08f),
                                    spotColor = Color.Black.copy(alpha = 0.12f)
                                )
                                .clip(CircleShape)
                                .background(Color.White)
                                .clickable {
                                    Toast.makeText(context, "Tìm kiếm", Toast.LENGTH_SHORT).show()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = "Search",
                                tint = Color(0xFF1E293B),
                                modifier = Modifier.size(17.dp)
                            )
                        }

                        // Notification Button with badge
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .shadow(
                                    elevation = 4.dp,
                                    shape = CircleShape,
                                    ambientColor = Color.Black.copy(alpha = 0.08f),
                                    spotColor = Color.Black.copy(alpha = 0.12f)
                                )
                                .clip(CircleShape)
                                .background(Color.White)
                                .clickable {
                                    Toast.makeText(context, "2 thông báo mới", Toast.LENGTH_SHORT).show()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_bell),
                                contentDescription = "Notifications",
                                tint = Color(0xFF1E293B),
                                modifier = Modifier.size(17.dp)
                            )
                            // Badge
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFEF4444))
                                    .border(1.5.dp, Color.White, CircleShape)
                                    .align(Alignment.TopEnd)
                                    .offset(x = 2.dp, y = (-2).dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "2",
                                    color = Color.White,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // ==================== 3. REVENUE CARD ====================
                RevenueCard(
                    revenue = "32.890.000",
                    growth = "+12%",
                    onClick = {
                        Toast.makeText(context, "Chi tiết doanh thu tháng 4", Toast.LENGTH_SHORT).show()
                    }
                )

                Spacer(modifier = Modifier.height(18.dp))

                // ==================== 4. QUICK ACTIONS GRID (2x4) ====================
                QuickActionsGrid(
                    onActionClick = { actionName ->
                        Toast.makeText(context, "Mở mục: $actionName", Toast.LENGTH_SHORT).show()
                    }
                )

                Spacer(modifier = Modifier.height(18.dp))

                // ==================== 5. ROOM STATUS DONUT CARD ====================
                RoomStatusCard(
                    totalRooms = 24,
                    rented = 18,
                    empty = 4,
                    deposit = 2
                )

                Spacer(modifier = Modifier.height(14.dp))

                // ==================== 6. INVOICES DUE CARD ====================
                InvoicesDueCard(
                    amount = "12.450.000đ",
                    count = 8,
                    onClick = {
                        Toast.makeText(context, "Xem danh sách 8 hóa đơn cần thu", Toast.LENGTH_SHORT).show()
                    }
                )

                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }
}

@Composable
private fun RevenueCard(
    revenue: String,
    growth: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF001A5E),
                        Color(0xFF032C95),
                        Color(0xFF0F3CB3)
                    )
                )
            )
            .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(14.dp))
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(14.dp),
                ambientColor = Color(0xFF032C95).copy(alpha = 0.35f),
                spotColor = Color(0xFF032C95).copy(alpha = 0.45f)
            )
            .clickable { onClick() }
            .padding(18.dp)
    ) {
        // Futuristic Graphic Curves & Data Node Canvas in the background
        Canvas(
            modifier = Modifier
                .fillMaxWidth(0.55f)
                .height(100.dp)
                .align(Alignment.BottomEnd)
        ) {
            val w = size.width
            val h = size.height

            // Glowing mesh area
            val areaPath = Path().apply {
                moveTo(0f, h * 0.9f)
                cubicTo(w * 0.3f, h * 0.9f, w * 0.5f, h * 0.45f, w * 0.7f, h * 0.55f)
                cubicTo(w * 0.85f, h * 0.6f, w * 0.9f, h * 0.2f, w, h * 0.1f)
                lineTo(w, h)
                lineTo(0f, h)
                close()
            }
            drawPath(
                path = areaPath,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF00E676).copy(alpha = 0.28f),
                        Color(0xFF38BDF8).copy(alpha = 0.10f),
                        Color.Transparent
                    )
                )
            )

            // Main glowing line
            val linePath = Path().apply {
                moveTo(0f, h * 0.9f)
                cubicTo(w * 0.3f, h * 0.9f, w * 0.5f, h * 0.45f, w * 0.7f, h * 0.55f)
                cubicTo(w * 0.85f, h * 0.6f, w * 0.9f, h * 0.2f, w, h * 0.1f)
            }
            drawPath(
                path = linePath,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF38BDF8).copy(alpha = 0.3f),
                        Color(0xFF67E8F9),
                        Color(0xFF00E676)
                    )
                ),
                style = Stroke(width = 2.5.dp.toPx(), cap = StrokeCap.Round)
            )

            // Peak Node
            drawCircle(
                color = Color(0xFF00E676).copy(alpha = 0.3f),
                radius = 6.dp.toPx(),
                center = Offset(w, h * 0.1f)
            )
            drawCircle(
                color = Color(0xFF00E676),
                radius = 3.dp.toPx(),
                center = Offset(w, h * 0.1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Doanh thu tháng 4",
                        color = Color(0xFFBFDBFE),
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF00E676))
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = revenue,
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = (-0.5).sp
                    )
                    Text(
                        text = "đ",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 2.dp, start = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF00B14F)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .size(9.dp)
                                .offset(y = (-1).dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = growth,
                        color = Color(0xFF00E676),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "so với tháng trước",
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 12.sp
                    )
                }
            }

            // Glassmorphism arrow button
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.12f))
                    .border(1.dp, Color.White.copy(alpha = 0.2f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_chevron_right),
                    contentDescription = "Details",
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}

@Composable
private fun QuickActionsGrid(
    onActionClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf<Pair<String, @Composable () -> Unit>>(
        "Chi nhánh" to { BranchDualIcon(modifier = Modifier.size(24.dp)) },
        "Hợp đồng" to { ContractDualIcon(modifier = Modifier.size(24.dp)) },
        "Tài khoản" to { BankAccountDualIcon(modifier = Modifier.size(24.dp)) },
        "Điện nước" to { UtilityDualIcon(modifier = Modifier.size(24.dp)) },
        "Lưu trú" to { TenantsDualIcon(modifier = Modifier.size(24.dp)) },
        "Thu chi" to { AccountingDualIcon(modifier = Modifier.size(24.dp)) },
        "Báo cáo" to { ReportsDualIcon(modifier = Modifier.size(24.dp)) },
        "Khác" to { MoreDualIcon(modifier = Modifier.size(24.dp)) }
    )

    Column(modifier = modifier.fillMaxWidth()) {
        for (rowIndex in 0..1) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (colIndex in 0..3) {
                    val item = items[rowIndex * 4 + colIndex]
                    val isOther = item.first == "Khác"
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onActionClick(item.first) }
                            .padding(vertical = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .shadow(
                                    elevation = 5.dp,
                                    shape = RoundedCornerShape(10.dp),
                                    ambientColor = Color(0xFF032C95).copy(alpha = 0.12f),
                                    spotColor = Color.Black.copy(alpha = 0.18f)
                                )
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (isOther) Color(0xFFF8FAFC) else Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            item.second()
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = item.first,
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isOther) Color(0xFF64748B) else Color(0xFF334155)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RoomStatusCard(
    totalRooms: Int,
    rented: Int,
    empty: Int,
    deposit: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color.Black.copy(alpha = 0.06f),
                spotColor = Color.Black.copy(alpha = 0.10f)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "Tình trạng phòng",
                color = TextPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.3).sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Donut Chart with white inner hole
                Box(
                    modifier = Modifier.size(104.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val stroke = 21.dp.toPx()
                        val rentedSweep = 360f * (rented.toFloat() / totalRooms)
                        val emptySweep = 360f * (empty.toFloat() / totalRooms)
                        val depositSweep = 360f * (deposit.toFloat() / totalRooms)

                        // Segment 1: Rented (Blue #0284C7)
                        drawArc(
                            color = Color(0xFF0284C7),
                            startAngle = -90f,
                            sweepAngle = rentedSweep,
                            useCenter = false,
                            style = Stroke(stroke)
                        )
                        // Segment 2: Empty (Amber #F59E0B)
                        drawArc(
                            color = Color(0xFFF59E0B),
                            startAngle = -90f + rentedSweep,
                            sweepAngle = emptySweep,
                            useCenter = false,
                            style = Stroke(stroke)
                        )
                        // Segment 3: Deposit (Slate #CBD5E1)
                        drawArc(
                            color = Color(0xFFCBD5E1),
                            startAngle = -90f + rentedSweep + emptySweep,
                            sweepAngle = depositSweep,
                            useCenter = false,
                            style = Stroke(stroke)
                        )
                    }

                    // White Donut Hole (like HTML donut-hole)
                    Box(
                        modifier = Modifier
                            .size(62.dp)
                            .shadow(2.dp, CircleShape, ambientColor = Color.Black.copy(alpha = 0.08f))
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$rented/$totalRooms",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = TextPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.width(20.dp))

                // Legends
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    LegendRow(label = "Đã thuê", count = rented, color = Color(0xFF0284C7))
                    LegendRow(label = "Trống", count = empty, color = Color(0xFFF59E0B))
                    LegendRow(label = "Đang cọc", count = deposit, color = Color(0xFFCBD5E1))
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Divider & Footer (matching HTML)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tổng $totalRooms phòng",
                    color = Color(0xFF64748B),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun LegendRow(label: String, count: Int, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(color)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF475569)
            )
        }
        Text(
            text = count.toString(),
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
    }
}

@Composable
private fun InvoicesDueCard(
    amount: String,
    count: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color.Black.copy(alpha = 0.06f),
                spotColor = Color.Black.copy(alpha = 0.10f)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Hóa đơn cần thu",
                    color = Color(0xFF1E293B),
                    fontSize = 12.5.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = amount,
                    color = BrandPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = (-0.3).sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "$count hóa đơn chưa thanh toán",
                    color = Color(0xFF94A3B8),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFDDE1FF).copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_forward),
                    contentDescription = null,
                    tint = BrandPrimary,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
