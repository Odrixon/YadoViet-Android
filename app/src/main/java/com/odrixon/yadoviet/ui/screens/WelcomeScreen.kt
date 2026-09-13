package com.odrixon.yadoviet.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odrixon.yadoviet.R
import com.odrixon.yadoviet.ui.theme.AppBackground
import com.odrixon.yadoviet.ui.theme.BrandPrimary
import com.odrixon.yadoviet.ui.theme.BrandPrimaryDark
import com.odrixon.yadoviet.ui.theme.TextMuted
import com.odrixon.yadoviet.ui.theme.TextPrimary
import com.odrixon.yadoviet.ui.theme.TextSecondary

@Composable
fun WelcomeScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
    ) {
        // Top Hero Background Image (Sky & Upper city)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.68f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.welcome_hero),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Top Content: Heading & Brand Name
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 36.dp)
        ) {
            Text(
                text = "Chào mừng bạn đến với",
                color = TextPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = (-0.5).sp
            )

            Text(
                text = "YadoViet",
                color = BrandPrimary,
                fontSize = 34.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = (-1).sp
            )

            // Hand-drawn style blue underline
            Canvas(
                modifier = Modifier
                    .width(96.dp)
                    .height(6.dp)
                    .padding(top = 2.dp)
            ) {
                val path = Path().apply {
                    moveTo(3f, size.height * 0.7f)
                    quadraticBezierTo(
                        size.width * 0.3f, size.height * 0.2f,
                        size.width * 0.7f, size.height * 0.8f
                    )
                    quadraticBezierTo(
                        size.width * 0.85f, size.height * 0.5f,
                        size.width * 0.97f, size.height * 0.35f
                    )
                }
                drawPath(
                    path = path,
                    color = BrandPrimary,
                    style = androidx.compose.ui.graphics.drawscope.Stroke(
                        width = 4.dp.toPx(),
                        cap = androidx.compose.ui.graphics.StrokeCap.Round
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Quản lý nhà trọ — Đơn giản hơn, dễ dàng hơn.",
                color = TextSecondary,
                fontSize = 13.5.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp
            )
        }

        // Bottom CTA Actions & Terms Container
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp)
                .padding(bottom = 28.dp)
        ) {
            // Primary Button: Đăng nhập (Gradient Pill)
            Button(
                onClick = onLoginClick,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .shadow(
                        elevation = 6.dp,
                        shape = CircleShape,
                        ambientColor = BrandPrimary.copy(alpha = 0.2f),
                        spotColor = BrandPrimary.copy(alpha = 0.35f)
                    )
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(BrandPrimary, Color(0xFF173BAB))
                        ),
                        shape = CircleShape
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_login),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Đăng nhập",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_forward),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Secondary Button: Đăng ký (Outlined Pill)
            OutlinedButton(
                onClick = onRegisterClick,
                shape = CircleShape,
                border = BorderStroke(1.5.dp, BrandPrimary),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = BrandPrimary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_person_add),
                        contentDescription = null,
                        tint = BrandPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Đăng ký",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandPrimary
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_forward),
                        contentDescription = null,
                        tint = BrandPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Terms & Privacy note
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Bằng việc tiếp tục, bạn đồng ý với",
                    color = TextMuted,
                    fontSize = 11.5.sp,
                    fontWeight = FontWeight.Medium
                )
                Row(
                    modifier = Modifier.padding(top = 2.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Điều khoản sử dụng",
                        color = BrandPrimary,
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = " và ",
                        color = TextMuted,
                        fontSize = 11.5.sp
                    )
                    Text(
                        text = "Chính sách bảo mật",
                        color = BrandPrimary,
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
