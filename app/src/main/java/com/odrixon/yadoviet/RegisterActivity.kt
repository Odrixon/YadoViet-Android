package com.odrixon.yadoviet

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.odrixon.yadoviet.ui.screens.RegisterScreen
import com.odrixon.yadoviet.ui.theme.YadoVietTheme
import com.odrixon.yadoviet.utils.finishNoAnimation
import com.odrixon.yadoviet.utils.startActivityAndFinishNoAnimation

class RegisterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            YadoVietTheme {
                RegisterScreen(
                    onBackClick = {
                        finishNoAnimation()
                    },
                    onLoginClick = {
                        startActivityAndFinishNoAnimation<LoginActivity>()
                    },
                    onRegisterClick = { fullName, phone ->
                        Toast.makeText(
                            this,
                            "Tạo tài khoản thành công cho $fullName - $phone",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
        }
    }
}
