package com.odrixon.yadoviet.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odrixon.yadoviet.R
import com.odrixon.yadoviet.ui.components.AppTextField
import com.odrixon.yadoviet.ui.components.PrimaryPillButton
import com.odrixon.yadoviet.ui.theme.AppBackground
import com.odrixon.yadoviet.ui.theme.BrandAccent
import com.odrixon.yadoviet.ui.theme.BrandPrimary
import com.odrixon.yadoviet.ui.theme.InputBorder
import com.odrixon.yadoviet.ui.theme.TextMuted
import com.odrixon.yadoviet.ui.theme.TextPrimary
import com.odrixon.yadoviet.ui.theme.TextSecondary

@Composable
fun RegisterScreen(
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit,
    onRegisterSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var currentStep by remember { mutableIntStateOf(1) }

    // Step 1 fields
    var hostelName by remember { mutableStateOf("") }
    var subdomain by remember { mutableStateOf("") }
    var managerName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    // Step 2 fields
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

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
            // Top Navigation & Step Indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (currentStep == 2) {
                            currentStep = 1
                        } else {
                            onBackClick()
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

                // Step Indicator Badge
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color(0xFFEFF6FF))
                        .border(1.dp, Color(0xFFDBEAFE), CircleShape)
                        .padding(horizontal = 12.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "BƯỚC $currentStep/2",
                        color = BrandPrimary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 0.5.sp
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

            if (currentStep == 1) {
                // ==================== STEP 1: THÔNG TIN NHÀ TRỌ & CÁ NHÂN ====================
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Thông tin nhà trọ & cá nhân",
                        color = TextPrimary,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-0.5).sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Điền thông tin cơ bản để bắt đầu khởi tạo hệ thống",
                        color = TextSecondary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Tên nhà trọ / Tòa nhà
                Column {
                    Text(
                        text = "Tên nhà trọ / Tòa nhà",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    AppTextField(
                        value = hostelName,
                        onValueChange = { hostelName = it },
                        placeholder = "Ví dụ: Căn hộ Anh Bình Cần Thơ",
                        leadingIcon = painterResource(id = R.drawable.ic_apartment)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Đường dẫn truy cập (Subdomain)
                Column {
                    Text(
                        text = "Đường dẫn truy cập",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    AppTextField(
                        value = subdomain,
                        onValueChange = { subdomain = it },
                        placeholder = "anhbinhcantho",
                        leadingIcon = painterResource(id = R.drawable.ic_apartment),
                        trailingIcon = {
                            Text(
                                text = ".yadoviet.io.vn",
                                color = TextSecondary,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Họ & tên người quản lý
                Column {
                    Text(
                        text = "Họ & tên người quản lý",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    AppTextField(
                        value = managerName,
                        onValueChange = { managerName = it },
                        placeholder = "Họ và tên",
                        leadingIcon = painterResource(id = R.drawable.ic_person_add)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Số điện thoại
                Column {
                    Text(
                        text = "Số điện thoại",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    AppTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        placeholder = "0987654321",
                        leadingIcon = painterResource(id = R.drawable.ic_smartphone),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Nút Tiếp tục
                PrimaryPillButton(
                    text = "Tiếp tục",
                    onClick = {
                        if (hostelName.trim().isEmpty()) {
                            Toast.makeText(context, "Vui lòng nhập tên nhà trọ / tòa nhà", Toast.LENGTH_SHORT).show()
                            return@PrimaryPillButton
                        }
                        if (subdomain.trim().isEmpty()) {
                            Toast.makeText(context, "Vui lòng nhập đường dẫn truy cập (subdomain)", Toast.LENGTH_SHORT).show()
                            return@PrimaryPillButton
                        }
                        if (managerName.trim().isEmpty()) {
                            Toast.makeText(context, "Vui lòng nhập họ tên người quản lý", Toast.LENGTH_SHORT).show()
                            return@PrimaryPillButton
                        }
                        if (phone.trim().isEmpty()) {
                            Toast.makeText(context, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show()
                            return@PrimaryPillButton
                        }
                        currentStep = 2
                    }
                )

            } else {
                // ==================== STEP 2: THIẾT LẬP MẬT KHẨU BẢO MẬT ====================
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Thiết lập mật khẩu bảo mật",
                        color = TextPrimary,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-0.5).sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Tạo mật khẩu an toàn để truy cập vào hệ thống quản lý",
                        color = TextSecondary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Thông tin khởi tạo Card (Summary box)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color(0xFFEFF6FF).copy(alpha = 0.8f))
                        .border(1.dp, Color(0xFFDBEAFE), RoundedCornerShape(14.dp))
                        .padding(14.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "THÔNG TIN KHỞI TẠO:",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = BrandPrimary
                            )
                            Text(
                                text = "Sửa",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2563EB),
                                modifier = Modifier.clickable { currentStep = 1 }
                            )
                        }

                        Text(
                            text = "• Tên nhà trọ: $hostelName",
                            fontSize = 12.5.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextPrimary
                        )
                        Text(
                            text = "• Domain: $subdomain.yadoviet.io.vn",
                            fontSize = 12.5.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = BrandPrimary
                        )
                        Text(
                            text = "• Người quản lý: $managerName ($phone)",
                            fontSize = 12.5.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Mật khẩu
                Column {
                    Text(
                        text = "Mật khẩu truy cập",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    AppTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = "Nhập mật khẩu (tối thiểu 6 ký tự)",
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
                                    contentDescription = null,
                                    tint = if (isPasswordVisible) BrandPrimary else TextMuted,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Xác nhận mật khẩu
                Column {
                    Text(
                        text = "Xác nhận mật khẩu",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    AppTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        placeholder = "Nhập lại mật khẩu",
                        leadingIcon = painterResource(id = R.drawable.ic_shield),
                        visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            IconButton(
                                onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_visibility),
                                    contentDescription = null,
                                    tint = if (isConfirmPasswordVisible) BrandPrimary else TextMuted,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Nút: Quay lại & Hoàn tất đăng ký
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = { currentStep = 1 },
                        shape = CircleShape,
                        border = BorderStroke(1.dp, InputBorder),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White,
                            contentColor = TextPrimary
                        ),
                        modifier = Modifier
                            .weight(0.35f)
                            .height(50.dp)
                    ) {
                        Text(
                            text = "Quay lại",
                            fontSize = 13.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    PrimaryPillButton(
                        text = "Hoàn tất đăng ký",
                        onClick = {
                            if (password.length < 6) {
                                Toast.makeText(context, "Mật khẩu cần tối thiểu 6 ký tự", Toast.LENGTH_SHORT).show()
                                return@PrimaryPillButton
                            }
                            if (password != confirmPassword) {
                                Toast.makeText(context, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show()
                                return@PrimaryPillButton
                            }
                            Toast.makeText(context, "Đăng ký thành công!", Toast.LENGTH_SHORT).show()
                            onRegisterSuccess()
                        },
                        modifier = Modifier.weight(0.65f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Link đã có tài khoản
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Đã có tài khoản? ",
                    color = TextSecondary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Đăng nhập tại đây",
                    color = BrandPrimary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onLoginClick() }
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}
