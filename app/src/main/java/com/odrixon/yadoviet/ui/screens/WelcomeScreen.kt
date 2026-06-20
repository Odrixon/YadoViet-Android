package com.odrixon.yadoviet.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odrixon.yadoviet.R
import com.odrixon.yadoviet.ui.theme.OnSurface
import com.odrixon.yadoviet.ui.theme.OnSurfaceVariant
import com.odrixon.yadoviet.ui.theme.Primary

@Composable
fun WelcomeScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF7FAFD))
    ) {
        // Top 60% Section - Decorative & Illustration
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFDBE1FF), Color(0xFFE8ECFF))
                    )
                )
        ) {
            // Hero illustration image or building icon centered
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(160.dp)
                )
            }
        }

        // Bottom 40% Section - Action Sheet
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Texts
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Chào mừng bạn đến với YadoViet",
                        color = OnSurface,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Giải pháp quản lý nhà trọ toàn diện và thông minh cho chủ trọ thế hệ mới.",
                        color = OnSurfaceVariant,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }

                // Action Buttons
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = onLoginClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Primary),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Đăng nhập", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.ic_login),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    OutlinedButton(
                        onClick = onRegisterClick,
                        border = BorderStroke(2.dp, Primary.copy(alpha = 0.2f)),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Primary),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Đăng ký tài khoản", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.ic_person_add),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                // Footer Identity
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Languages
                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Tiếng Việt",
                            color = Primary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                Toast.makeText(context, "Đã chọn Tiếng Việt", Toast.LENGTH_SHORT).show()
                            }
                        )
                        Text(
                            text = "English",
                            color = OnSurfaceVariant,
                            fontSize = 11.sp,
                            modifier = Modifier.clickable {
                                Toast.makeText(context, "English selected", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }
        }
    }
}
