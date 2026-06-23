package com.odrixon.yadoviet.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
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
fun RegisterScreen(
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    var fullName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }
    var acceptTerms by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
            .heightIn(min = screenHeight - 40.dp)
    ) {
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

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFE0E3E6)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.odrixon_logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .height(84.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Tạo tài khoản",
                    color = OnSurface,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Bắt đầu quản lý nhà trọ của bạn cùng YadoViet",
                    color = OnSurfaceVariant,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(22.dp))

                RegisterTextField(
                    label = "Họ và tên",
                    value = fullName,
                    onValueChange = { fullName = it },
                    placeholder = "Nhập họ và tên",
                    icon = R.drawable.ic_person_add,
                    keyboardType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(14.dp))

                RegisterTextField(
                    label = "Số điện thoại",
                    value = phone,
                    onValueChange = { phone = it },
                    placeholder = "09xx xxx xxx",
                    icon = R.drawable.ic_smartphone,
                    keyboardType = KeyboardType.Phone
                )

                Spacer(modifier = Modifier.height(14.dp))

                RegisterPasswordField(
                    label = "Mật khẩu",
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Nhập mật khẩu",
                    isVisible = isPasswordVisible,
                    onToggleVisible = { isPasswordVisible = !isPasswordVisible }
                )

                Spacer(modifier = Modifier.height(14.dp))

                RegisterPasswordField(
                    label = "Xác nhận mật khẩu",
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    placeholder = "Nhập lại mật khẩu",
                    isVisible = isConfirmPasswordVisible,
                    onToggleVisible = { isConfirmPasswordVisible = !isConfirmPasswordVisible }
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = acceptTerms,
                        onCheckedChange = { acceptTerms = it },
                        colors = CheckboxDefaults.colors(checkedColor = Primary)
                    )
                    Text(
                        text = buildAnnotatedString {
                            append("Tôi đồng ý với ")
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = Primary)) {
                                append("thỏa thuận người dùng")
                            }
                            append(" và ")
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = Primary)) {
                                append("chính sách bảo mật")
                            }
                        },
                        color = OnSurface,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    onClick = {
                        val trimmedName = fullName.trim()
                        val trimmedPhone = phone.trim()

                        when {
                            trimmedName.isEmpty() -> {
                                Toast.makeText(context, "Vui lòng nhập họ và tên", Toast.LENGTH_SHORT).show()
                            }
                            trimmedPhone.isEmpty() -> {
                                Toast.makeText(context, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show()
                            }
                            password.length < 6 -> {
                                Toast.makeText(context, "Mật khẩu cần tối thiểu 6 ký tự", Toast.LENGTH_SHORT).show()
                            }
                            password != confirmPassword -> {
                                Toast.makeText(context, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show()
                            }
                            !acceptTerms -> {
                                Toast.makeText(context, "Vui lòng đồng ý điều khoản sử dụng", Toast.LENGTH_SHORT).show()
                            }
                            else -> onRegisterClick(trimmedName, trimmedPhone)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Text(
                        text = "Đăng ký",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = buildAnnotatedString {
                append("Đã có tài khoản? ")
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = Primary)) {
                    append("Đăng nhập")
                }
            },
            color = OnSurfaceVariant,
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onLoginClick() }
                .padding(bottom = 24.dp)
        )
    }
}

@Composable
private fun RegisterTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: Int,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier
) {
    Text(
        text = label,
        color = OnSurfaceVariant,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(6.dp))
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
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
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
private fun RegisterPasswordField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isVisible: Boolean,
    onToggleVisible: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = label,
        color = OnSurfaceVariant,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(6.dp))
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_lock),
                contentDescription = null,
                tint = Outline,
                modifier = Modifier.size(20.dp)
            )
        },
        trailingIcon = {
            IconButton(onClick = onToggleVisible) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_visibility),
                    contentDescription = "Toggle password visibility",
                    tint = Outline,
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        singleLine = true,
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Primary,
            unfocusedBorderColor = OutlineVariant,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = SurfaceContainerLow
        ),
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = modifier.fillMaxWidth()
    )
}
