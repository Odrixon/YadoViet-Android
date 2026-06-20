package com.odrixon.yadoviet.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
fun ForgetPasswordScreen(
    onBackClick: () -> Unit,
    onSendOtpClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var phone by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
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

        Spacer(modifier = Modifier.height(40.dp))

        // Welcome / Description
        Text(
            text = "Quên mật khẩu?",
            color = OnSurface,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Nhập số điện thoại đã đăng ký để nhận mã xác thực (OTP)",
            color = OnSurfaceVariant,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Form Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFE0E3E6)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                // Phone input field
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
                    placeholder = { Text("09xx xxx xxx") },
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

                Spacer(modifier = Modifier.height(24.dp))

                // Submit Button with End Icon
                Button(
                    onClick = {
                        if (phone.trim().isEmpty()) {
                            Toast.makeText(context, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show()
                            return@Button
                        }
                        onSendOtpClick(phone)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Gửi mã OTP", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_forward),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Help Footer Info
        Text(
            text = "Gặp khó khăn? Liên hệ Hỗ trợ",
            color = OnSurfaceVariant,
            fontSize = 13.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 24.dp)
        )
    }
}
