package com.odrixon.yadoviet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.odrixon.yadoviet.ui.screens.WebManageScreen
import com.odrixon.yadoviet.ui.theme.YadoVietTheme
import com.odrixon.yadoviet.utils.finishNoAnimation

class WebManageActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val targetUrl = intent.getStringExtra("EXTRA_URL") ?: "http://localhost:3000/manage/branch"
        val title = intent.getStringExtra("EXTRA_TITLE") ?: "YadoViet Quản lý"

        setContent {
            YadoVietTheme {
                WebManageScreen(
                    initialUrl = targetUrl,
                    title = title,
                    onCloseClick = {
                        finishNoAnimation()
                    }
                )
            }
        }
    }
}
