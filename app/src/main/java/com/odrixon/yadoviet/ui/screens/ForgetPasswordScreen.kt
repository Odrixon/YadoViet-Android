package com.odrixon.yadoviet.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odrixon.yadoviet.R
import com.odrixon.yadoviet.ui.components.AppTextField
import com.odrixon.yadoviet.ui.components.PrimaryPillButton
import com.odrixon.yadoviet.ui.theme.AppBackground
import com.odrixon.yadoviet.ui.theme.BrandAccent
import com.odrixon.yadoviet.ui.theme.BrandPrimary
import com.odrixon.yadoviet.ui.theme.InputBorder
import com.odrixon.yadoviet.ui.theme.InputBorderFocus
import com.odrixon.yadoviet.ui.theme.InputBackground
import com.odrixon.yadoviet.ui.theme.TextMuted
import com.odrixon.yadoviet.ui.theme.TextPrimary
import com.odrixon.yadoviet.ui.theme.TextSecondary
import kotlinx.coroutines.delay

@Composable
fun ForgetPasswordScreen(
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var currentStep by remember { mutableIntStateOf(1) }

    // Step 1: Input
    var contactInput by remember { mutableStateOf("") }

    // Step 2: OTP
    var otpCode by remember { mutableStateOf("") }
    var timeLeft by remember { mutableIntStateOf(105) } // 01:45
    val focusRequester = remember { FocusRequester() }

    // Step 3: New Password
    var newPassword by remember { mutableStateOf("") }
    var confirmNewPassword by remember { mutableStateOf("") }
    var isNewPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmNewPasswordVisible by remember { mutableStateOf(false) }

    // Countdown Timer logic for Step 2
    LaunchedEffect(currentStep, timeLeft) {
        if (currentStep == 2 && timeLeft > 0) {
            delay(1000)
            timeLeft--
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        // Bottom Background Illustration
        Image(
            painter = painterResource(id = R.drawable.bottom_widget),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .align(Alignment.BottomCenter)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 24.dp)
        ) {
            // Top Navigation & Step Indicator (hidden on step 4)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        when (currentStep) {
                            1 -> onBackClick()
                            2 -> currentStep = 1
                            3 -> currentStep = 2
                            4 -> onLoginClick()
                        }
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Quay lại",
                        tint = TextPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                if (currentStep in 1..3) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color(0xFFEFF6FF))
                            .border(1.dp, Color(0xFFDBEAFE), CircleShape)
                            .padding(horizontal = 12.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = "BƯỚC $currentStep/3",
                            color = BrandPrimary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 0.5.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Brand Logo Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_icon),
                    contentDescription = "YadoViet Logo",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.width(10.dp))
                Row(verticalAlignment = Alignment.Top) {
                    Text(
                        text = "YadoViet",
                        color = BrandPrimary,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-0.5).sp
                    )
                    Text(
                        text = "®",
                        color = BrandAccent,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            when (currentStep) {
                // ==================== STEP 1: YÊU CẦU QUÊN MẬT KHẨU ====================
                1 -> {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Quên mật khẩu?",
                            color = TextPrimary,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = (-0.5).sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Nhập số điện thoại hoặc email đã đăng ký của bạn. Chúng tôi sẽ gửi mã xác thực OTP.",
                            color = TextSecondary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 18.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Column {
                        Text(
                            text = "Số điện thoại hoặc Email",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        AppTextField(
                            value = contactInput,
                            onValueChange = { contactInput = it },
                            placeholder = "Ví dụ: 0987654321 hoặc email@domain.com",
                            leadingIcon = painterResource(id = R.drawable.ic_sms)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    PrimaryPillButton(
                        text = "Gửi mã xác thực OTP",
                        onClick = {
                            if (contactInput.trim().isEmpty()) {
                                Toast.makeText(context, "Vui lòng nhập số điện thoại hoặc email", Toast.LENGTH_SHORT).show()
                                return@PrimaryPillButton
                            }
                            timeLeft = 105
                            currentStep = 2
                        }
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Nhớ mật khẩu rồi? ",
                            color = TextSecondary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Đăng nhập ngay",
                            color = BrandPrimary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable { onLoginClick() }
                        )
                    }
                }

                // ==================== STEP 2: NHẬP MÃ OTP ====================
                2 -> {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Xác thực mã OTP",
                            color = TextPrimary,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = (-0.5).sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Mã xác thực gồm 6 số đã được gửi tới\n$contactInput",
                            color = TextSecondary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 18.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // OTP 6 digit boxes
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { focusRequester.requestFocus() },
                        contentAlignment = Alignment.Center
                    ) {
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
                                .size(1.dp)
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            for (i in 0 until 6) {
                                val char = if (i < otpCode.length) otpCode[i].toString() else ""
                                val isFocused = i == otpCode.length

                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(0.85f)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(InputBackground)
                                        .border(
                                            width = if (isFocused) 2.dp else 1.dp,
                                            color = if (isFocused) InputBorderFocus else InputBorder,
                                            shape = RoundedCornerShape(12.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = char,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = BrandPrimary,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Countdown Timer & Resend Button
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val minutes = timeLeft / 60
                        val seconds = timeLeft % 60
                        val timeFormatted = String.format("%02d:%02d", minutes, seconds)

                        Text(
                            text = "Hết hạn sau: $timeFormatted",
                            color = if (timeLeft < 30) Color(0xFFEF4444) else TextSecondary,
                            fontSize = 12.5.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "Gửi lại mã",
                            color = BrandPrimary,
                            fontSize = 12.5.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                timeLeft = 105
                                otpCode = ""
                                Toast.makeText(context, "Đã gửi lại mã OTP mới!", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    PrimaryPillButton(
                        text = "Xác thực mã OTP",
                        onClick = {
                            if (otpCode.length < 6) {
                                Toast.makeText(context, "Vui lòng nhập đủ 6 chữ số OTP", Toast.LENGTH_SHORT).show()
                                return@PrimaryPillButton
                            }
                            currentStep = 3
                        }
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "← Thay đổi số điện thoại / email",
                        color = TextSecondary,
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { currentStep = 1 }
                    )
                }

                // ==================== STEP 3: NHẬP LẠI MẬT KHẨU MỚI ====================
                3 -> {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Đặt lại mật khẩu mới",
                            color = TextPrimary,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = (-0.5).sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Vui lòng nhập mật khẩu mới bảo mật và dễ nhớ cho tài khoản của bạn.",
                            color = TextSecondary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 18.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    // Mật khẩu mới
                    Column {
                        Text(
                            text = "Mật khẩu mới",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        AppTextField(
                            value = newPassword,
                            onValueChange = { newPassword = it },
                            placeholder = "Nhập mật khẩu mới",
                            leadingIcon = painterResource(id = R.drawable.ic_lock),
                            visualTransformation = if (isNewPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            trailingIcon = {
                                IconButton(
                                    onClick = { isNewPasswordVisible = !isNewPasswordVisible },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_visibility),
                                        contentDescription = null,
                                        tint = if (isNewPasswordVisible) BrandPrimary else TextMuted,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Xác nhận mật khẩu mới
                    Column {
                        Text(
                            text = "Xác nhận lại mật khẩu mới",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        AppTextField(
                            value = confirmNewPassword,
                            onValueChange = { confirmNewPassword = it },
                            placeholder = "Nhập lại mật khẩu mới",
                            leadingIcon = painterResource(id = R.drawable.ic_shield),
                            visualTransformation = if (isConfirmNewPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            trailingIcon = {
                                IconButton(
                                    onClick = { isConfirmNewPasswordVisible = !isConfirmNewPasswordVisible },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_visibility),
                                        contentDescription = null,
                                        tint = if (isConfirmNewPasswordVisible) BrandPrimary else TextMuted,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Password Strength Indicator
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Độ bảo mật mật khẩu",
                                fontSize = 11.5.sp,
                                color = TextSecondary,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "Mạnh (Tốt)",
                                fontSize = 11.5.sp,
                                color = BrandPrimary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Box(modifier = Modifier.weight(1f).height(5.dp).clip(CircleShape).background(BrandPrimary))
                            Box(modifier = Modifier.weight(1f).height(5.dp).clip(CircleShape).background(BrandPrimary))
                            Box(modifier = Modifier.weight(1f).height(5.dp).clip(CircleShape).background(BrandPrimary))
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    PrimaryPillButton(
                        text = "Xác nhận đổi mật khẩu",
                        onClick = {
                            if (newPassword.length < 6) {
                                Toast.makeText(context, "Mật khẩu cần tối thiểu 6 ký tự", Toast.LENGTH_SHORT).show()
                                return@PrimaryPillButton
                            }
                            if (newPassword != confirmNewPassword) {
                                Toast.makeText(context, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show()
                                return@PrimaryPillButton
                            }
                            currentStep = 4
                        }
                    )
                }

                // ==================== STEP 4: ĐẶT LẠI MẬT KHẨU THÀNH CÔNG ====================
                4 -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Success Badge
                        Box(
                            modifier = Modifier
                                .size(76.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFECFDF5))
                                .border(4.dp, Color(0xFFD1FAE5), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_shield),
                                contentDescription = null,
                                tint = Color(0xFF10B981),
                                modifier = Modifier.size(38.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        Text(
                            text = "Đặt lại mật khẩu thành công!",
                            color = TextPrimary,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Mật khẩu của bạn đã được cập nhật an toàn. Bạn có thể sử dụng mật khẩu mới để đăng nhập ngay bây giờ.",
                            color = TextSecondary,
                            fontSize = 13.5.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            lineHeight = 20.sp
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        PrimaryPillButton(
                            text = "Đăng nhập ngay",
                            onClick = { onLoginClick() }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
