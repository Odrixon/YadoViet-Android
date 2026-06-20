package com.odrixon.yadoviet

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.odrixon.yadoviet.ui.screens.RegisterScreen
import com.odrixon.yadoviet.ui.theme.YadoVietTheme

class RegisterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            YadoVietTheme {
                RegisterScreen(
                    onBackClick = {
                        finish()
                        overridePendingTransition(0, 0)
                    },
                    onLoginClick = {
                        startActivity(Intent(this, LoginActivity::class.java))
                        overridePendingTransition(0, 0)
                        finish()
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
