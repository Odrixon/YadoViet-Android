package com.odrixon.yadoviet.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odrixon.yadoviet.R
import com.odrixon.yadoviet.ui.components.AppTextField
import com.odrixon.yadoviet.ui.components.GoogleLoginButton
import com.odrixon.yadoviet.ui.components.PrimaryPillButton
import com.odrixon.yadoviet.ui.theme.AppBackground
import com.odrixon.yadoviet.ui.theme.BrandAccent
import com.odrixon.yadoviet.ui.theme.BrandPrimary
import com.odrixon.yadoviet.ui.theme.DividerColor
import com.odrixon.yadoviet.ui.theme.InputBorder
import com.odrixon.yadoviet.ui.theme.TextMuted
import com.odrixon.yadoviet.ui.theme.TextPrimary
import com.odrixon.yadoviet.ui.theme.TextSecondary

@Composable
fun LoginScreen(
    onBackClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var phoneOrEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var rememberLogin by remember { mutableStateOf(true) }

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

        // Main Scrollable Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 24.dp)
        ) {
            // Top Navigation / Back Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
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
                        .size(40.dp)
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
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Form Heading
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Đăng nhập",
                    color = TextPrimary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = (-0.5).sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Chào mừng bạn trở lại!\nVui lòng đăng nhập để tiếp tục.",
                    color = TextSecondary,
                    fontSize = 13.5.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 19.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Phone or Email Input
            AppTextField(
                value = phoneOrEmail,
                onValueChange = { phoneOrEmail = it },
                placeholder = "Số điện thoại hoặc email",
                leadingIcon = painterResource(id = R.drawable.ic_smartphone),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Password Input
            AppTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Mật khẩu",
                leadingIcon = painterResource(id = R.drawable.ic_lock),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(
                        onClick = { isPasswordVisible = !isPasswordVisible },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_visibility),
                            contentDescription = if (isPasswordVisible) "Ẩn mật khẩu" else "Hiện mật khẩu",
                            tint = if (isPasswordVisible) BrandPrimary else TextMuted,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Remember Me & Forgot Password Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            rememberLogin = !rememberLogin
                        }
                ) {
                    Checkbox(
                        checked = rememberLogin,
                        onCheckedChange = { rememberLogin = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = BrandPrimary,
                            uncheckedColor = InputBorder
                        ),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Nhớ tài khoản",
                        color = TextSecondary,
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Text(
                    text = "Quên mật khẩu?",
                    color = BrandPrimary,
                    fontSize = 12.5.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            onForgotPasswordClick()
                        }
                        .padding(vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Submit Button
            PrimaryPillButton(
                text = "Đăng nhập",
                onClick = {
                    if (phoneOrEmail.trim().isEmpty()) {
                        Toast.makeText(context, "Vui lòng nhập số điện thoại hoặc email", Toast.LENGTH_SHORT).show()
                        return@PrimaryPillButton
                    }
                    if (password.trim().isEmpty()) {
                        Toast.makeText(context, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show()
                        return@PrimaryPillButton
                    }
                    Toast.makeText(context, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Divider "HOẶC"
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 1.dp,
                    color = DividerColor
                )
                Text(
                    text = "HOẶC",
                    color = TextMuted,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp),
                    letterSpacing = 1.sp
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 1.dp,
                    color = DividerColor
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Google Login Button
            GoogleLoginButton(
                onClick = {
                    Toast.makeText(context, "Đang kết nối Google Sign-in...", Toast.LENGTH_SHORT).show()
                }
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Register Link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Chưa có tài khoản? ",
                    color = TextSecondary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Đăng ký ngay",
                    color = BrandPrimary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        onRegisterClick()
                    }
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
