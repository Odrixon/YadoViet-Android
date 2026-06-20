package com.odrixon.yadoviet.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odrixon.yadoviet.R
import com.odrixon.yadoviet.ui.theme.Background
import com.odrixon.yadoviet.ui.theme.OnSurface
import com.odrixon.yadoviet.ui.theme.OnSurfaceVariant
import com.odrixon.yadoviet.ui.theme.Outline
import com.odrixon.yadoviet.ui.theme.OutlineVariant
import com.odrixon.yadoviet.ui.theme.Primary
import com.odrixon.yadoviet.ui.theme.SurfaceContainerLow

@Composable
fun LoginScreen(
    onBackClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var rememberLogin by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
            .heightIn(min = screenHeight - 40.dp), // subtract top and bottom paddings (20.dp each)
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top section (Header and Welcome)
        Column {
            // Top Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Back",
                        tint = Primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "YadoViet",
                    color = Primary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }

        // Bottom section (Login Card and Footer links)
        Column {
            Spacer(modifier = Modifier.height(24.dp))

            // Login Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFE0E3E6)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    // App Logo
                    Image(
                        painter = painterResource(id = R.drawable.odrixon_logo),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .height(96.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Welcome Header (Moved inside the card)
                    Text(
                        text = "Chào mừng quay trở lại",
                        color = OnSurface,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "Vui lòng đăng nhập để quản lý tài chính của bạn",
                        color = OnSurfaceVariant,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 6.dp).align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Phone Label & Field
                    Text(
                        text = "Số điện thoại",
                        color = OnSurfaceVariant,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        placeholder = { Text("Nhập số điện thoại") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_smartphone),
                                contentDescription = "Phone Icon",
                                tint = Outline,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Primary,
                            unfocusedBorderColor = OutlineVariant,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = SurfaceContainerLow
                        ),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Password Label & Field
                    Text(
                        text = "Mật khẩu",
                        color = OnSurfaceVariant,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text("Nhập mật khẩu") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_lock),
                                contentDescription = "Lock Icon",
                                tint = Outline,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_visibility),
                                    contentDescription = "Toggle password visibility",
                                    tint = Outline,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        },
                        singleLine = true,
                        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Primary,
                            unfocusedBorderColor = OutlineVariant,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = SurfaceContainerLow
                        ),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Remember & Forgot Password Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = rememberLogin,
                                onCheckedChange = { rememberLogin = it },
                                colors = CheckboxDefaults.colors(checkedColor = Primary)
                            )
                            Text(
                                text = "Ghi nhớ đăng nhập",
                                color = OnSurface,
                                fontSize = 13.sp
                            )
                        }

                        Text(
                            text = "Quên mật khẩu?",
                            color = Primary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clickable { onForgotPasswordClick() }
                                .padding(4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Submit button
                    Button(
                        onClick = {
                            if (phone.trim().isEmpty()) {
                                Toast.makeText(context, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            if (password.trim().isEmpty()) {
                                Toast.makeText(context, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            Toast.makeText(context, "Đăng nhập thành công với SĐT: $phone", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Primary),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                    ) {
                        Text("Đăng nhập", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = androidx.compose.ui.text.buildAnnotatedString {
                    append("Khi đăng ký trên YadoViet, bạn đã đồng ý với các ")
                    withStyle(
                        style = androidx.compose.ui.text.SpanStyle(fontWeight = FontWeight.Bold)
                    ) {
                        append("thỏa thuận người dùng")
                    }
                    append(" và ")
                    withStyle(
                        style = androidx.compose.ui.text.SpanStyle(fontWeight = FontWeight.Bold)
                    ) {
                        append("chính sách bảo mật")
                    }
                    append(" của chúng tôi")
                },
                color = OnSurfaceVariant,
                fontSize = 11.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
        }
    }
}
