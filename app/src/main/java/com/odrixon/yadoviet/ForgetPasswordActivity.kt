package com.odrixon.yadoviet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.odrixon.yadoviet.ui.screens.ForgetPasswordScreen
import com.odrixon.yadoviet.ui.theme.YadoVietTheme
import com.odrixon.yadoviet.utils.finishNoAnimation
import com.odrixon.yadoviet.utils.startActivityNoAnimation

class ForgetPasswordActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            YadoVietTheme {
                ForgetPasswordScreen(
                    onBackClick = {
                        finishNoAnimation()
                    },
                    onSendOtpClick = { phone ->
                        startActivityNoAnimation<OtpActivity> {
                            putExtra("EXTRA_PHONE", phone)
                        }
                    }
                )
            }
        }
    }
}
