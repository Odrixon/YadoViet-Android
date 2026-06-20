package com.odrixon.yadoviet

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.odrixon.yadoviet.ui.screens.WelcomeScreen
import com.odrixon.yadoviet.ui.theme.YadoVietTheme

class WelcomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            YadoVietTheme {
                WelcomeScreen(
                    onLoginClick = {
                        startActivity(Intent(this, LoginActivity::class.java))
                        overridePendingTransition(0, 0)
                    },
                    onRegisterClick = {
                        Toast.makeText(this, "Tính năng đăng ký tài khoản sẽ sớm ra mắt!", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}
