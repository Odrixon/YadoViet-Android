package com.odrixon.yadoviet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.odrixon.yadoviet.ui.screens.WelcomeScreen
import com.odrixon.yadoviet.ui.theme.YadoVietTheme
import com.odrixon.yadoviet.utils.startActivityNoAnimation

class WelcomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            YadoVietTheme {
                WelcomeScreen(
                    onLoginClick = {
                        startActivityNoAnimation<LoginActivity>()
                    },
                    onRegisterClick = {
                        startActivityNoAnimation<RegisterActivity>()
                    }
                )
            }
        }
    }
}
