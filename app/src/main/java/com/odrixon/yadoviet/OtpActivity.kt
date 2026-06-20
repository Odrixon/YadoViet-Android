package com.odrixon.yadoviet

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.odrixon.yadoviet.ui.screens.OtpScreen
import com.odrixon.yadoviet.ui.theme.YadoVietTheme

class OtpActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val phone = intent.getStringExtra("EXTRA_PHONE") ?: "09xx xxx xxx"

        setContent {
            YadoVietTheme {
                OtpScreen(
                    phone = phone,
                    onBackClick = {
                        finish()
                        overridePendingTransition(0, 0)
                    },
                    onConfirmClick = { otpCode ->
                        Toast.makeText(this, "Xác minh mã OTP $otpCode thành công!", Toast.LENGTH_LONG).show()
                        // In a real app, navigate to reset password page
                        finish()
                        overridePendingTransition(0, 0)
                    }
                )
            }
        }
    }
}
