package com.odrixon.yadoviet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.odrixon.yadoviet.ui.screens.MainDashboardScreen
import com.odrixon.yadoviet.ui.theme.YadoVietTheme
import com.odrixon.yadoviet.utils.startActivityNoAnimation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            YadoVietTheme {
                MainDashboardScreen(
                    onNavigateToBranch = {
                        startActivityNoAnimation<WebManageActivity> {
                            putExtra("EXTRA_URL", "http://localhost:3000/manage/branch")
                            putExtra("EXTRA_TITLE", "Chi nhánh")
                        }
                    },
                    onNavigateToRooms = {
                        startActivityNoAnimation<WebManageActivity> {
                            putExtra("EXTRA_URL", "http://localhost:3000/manage/room")
                            putExtra("EXTRA_TITLE", "Quản lý phòng")
                        }
                    },
                    onNavigateToInvoices = {
                        startActivityNoAnimation<WebManageActivity> {
                            putExtra("EXTRA_URL", "http://localhost:3000/manage/invoice")
                            putExtra("EXTRA_TITLE", "Hóa đơn")
                        }
                    },
                    onNavigateToProfile = {
                        startActivityNoAnimation<WebManageActivity> {
                            putExtra("EXTRA_URL", "http://localhost:3000/manage/profile")
                            putExtra("EXTRA_TITLE", "Quản lý hồ sơ")
                        }
                    },
                    onNavigateToWeb = { url, title ->
                        startActivityNoAnimation<WebManageActivity> {
                            putExtra("EXTRA_URL", url)
                            putExtra("EXTRA_TITLE", title)
                        }
                    }
                )
            }
        }
    }
}
