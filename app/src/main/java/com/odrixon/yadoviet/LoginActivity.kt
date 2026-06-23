package com.odrixon.yadoviet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.odrixon.yadoviet.ui.screens.LoginScreen
import com.odrixon.yadoviet.ui.theme.YadoVietTheme
import com.odrixon.yadoviet.utils.finishNoAnimation
import com.odrixon.yadoviet.utils.startActivityNoAnimation

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            YadoVietTheme {
                LoginScreen(
                    onBackClick = {
                        finishNoAnimation()
                    },
                    onForgotPasswordClick = {
                        startActivityNoAnimation<ForgetPasswordActivity>()
                    }
                )
            }
        }
    }
}
