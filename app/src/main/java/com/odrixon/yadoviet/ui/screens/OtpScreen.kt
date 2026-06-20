package com.odrixon.yadoviet.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odrixon.yadoviet.R
import com.odrixon.yadoviet.ui.theme.Background
import com.odrixon.yadoviet.ui.theme.OnSurface
import com.odrixon.yadoviet.ui.theme.OnSurfaceVariant
import com.odrixon.yadoviet.ui.theme.OutlineVariant
import com.odrixon.yadoviet.ui.theme.Primary
import kotlinx.coroutines.delay

@Composable
fun OtpScreen(
    phone: String,
    onBackClick: () -> Unit,
    onConfirmClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var otpCode by remember { mutableStateOf("") }
    var timeLeft by remember { mutableIntStateOf(59) }
    var isTimerFinished by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    // Auto-focus keyboard on launch
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    // Countdown Timer logic
    LaunchedEffect(timeLeft) {
        if (timeLeft > 0) {
            delay(1000)
            timeLeft--
        } else {
            isTimerFinished = true
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        // Custom Header Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "Back",
                    tint = OnSurface,
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(
                text = "Xác minh OTP",
                color = OnSurface,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Shield Illustration in the center
        Box(
            modifier = Modifier
                .size(96.dp)
                .background(Color(0xFFDBE1FF), CircleShape)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_shield),
                contentDescription = null,
                tint = Primary,
                modifier = Modifier.size(48.dp)
            )
            
            // Small badge for SMS
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(Color(0xFF25CAFF), RoundedCornerShape(10.dp))
                    .align(Alignment.BottomEnd)
                    .offset(x = (2).dp, y = (2).dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sms),
                    contentDescription = null,
                    tint = Color(0xFF00526A),
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Title and Description
        Text(
            text = "Nhập mã xác thực",
            color = OnSurface,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Mã OTP đã được gửi đến số điện thoại $phone",
            color = OnSurfaceVariant,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // OTP Input Grid (6 Digits)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusRequester.requestFocus() },
            contentAlignment = Alignment.Center
        ) {
            // Hidden BasicTextField to receive input
            BasicTextField(
                value = otpCode,
                onValueChange = {
                    if (it.length <= 6 && it.all { char -> char.isDigit() }) {
                        otpCode = it
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .size(1.dp) // minimized
            )

            // Customized grid view for OTP display
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { focusRequester.requestFocus() }
            ) {
                for (i in 0 until 6) {
                    val char = if (i < otpCode.length) otpCode[i].toString() else ""
                    val isFocused = i == otpCode.length

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .border(
                                width = if (isFocused) 2.dp else 1.dp,
                                color = if (isFocused) Primary else OutlineVariant,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurface,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Timer & Resend Section
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (!isTimerFinished) {
                Text(
                    text = "Gửi lại mã sau ${timeLeft}s",
                    color = OnSurfaceVariant,
                    fontSize = 12.sp
                )
            } else {
                Text(
                    text = "Gửi lại mã",
                    color = Primary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable {
                            timeLeft = 59
                            isTimerFinished = false
                            otpCode = ""
                            Toast.makeText(context, "Một mã OTP mới đã được gửi!", Toast.LENGTH_SHORT).show()
                        }
                        .padding(4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Confirm Button
        Button(
            onClick = {
                if (otpCode.length < 6) {
                    Toast.makeText(context, "Vui lòng nhập đủ 6 chữ số OTP", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                onConfirmClick(otpCode)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Xác nhận", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_chevron_right),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Help Footer Info
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Gặp khó khăn? ",
                color = OnSurfaceVariant,
                fontSize = 12.sp
            )
            Text(
                text = "Liên hệ Hỗ trợ",
                color = Primary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    Toast.makeText(context, "Liên hệ Hỗ trợ Clicked", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}
