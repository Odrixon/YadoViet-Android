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
                    },
                    onTermsClick = {
                        startActivityNoAnimation<WebManageActivity> {
                            putExtra("EXTRA_URL", "http://localhost:3000/terms")
                            putExtra("EXTRA_TITLE", "Điều khoản sử dụng")
                        }
                    },
                    onPrivacyClick = {
                        startActivityNoAnimation<WebManageActivity> {
                            putExtra("EXTRA_URL", "http://localhost:3000/privacy")
                            putExtra("EXTRA_TITLE", "Chính sách bảo mật")
                        }
                    }
                )
            }
        }
    }
}
