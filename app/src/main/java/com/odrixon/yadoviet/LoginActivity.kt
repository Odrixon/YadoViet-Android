package com.odrixon.yadoviet

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.odrixon.yadoviet.ui.screens.LoginScreen
import com.odrixon.yadoviet.ui.theme.YadoVietTheme

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            YadoVietTheme {
                LoginScreen(
                    onBackClick = {
                        finish()
                        overridePendingTransition(0, 0)
                    },
                    onForgotPasswordClick = {
                        val intent = Intent(this, ForgetPasswordActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(0, 0)
                    }
                )
            }
        }
    }
}
