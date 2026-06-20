package com.odrixon.yadoviet

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.odrixon.yadoviet.ui.screens.ForgetPasswordScreen
import com.odrixon.yadoviet.ui.theme.YadoVietTheme

class ForgetPasswordActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            YadoVietTheme {
                ForgetPasswordScreen(
                    onBackClick = {
                        finish()
                        overridePendingTransition(0, 0)
                    },
                    onSendOtpClick = { phone ->
                        val intent = Intent(this, OtpActivity::class.java).apply {
                            putExtra("EXTRA_PHONE", phone)
                        }
                        startActivity(intent)
                        overridePendingTransition(0, 0)
                    }
                )
            }
        }
    }
}
