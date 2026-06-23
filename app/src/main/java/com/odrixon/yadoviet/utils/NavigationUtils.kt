package com.odrixon.yadoviet.utils

import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 * Hỗ trợ chuyển Activity hoặc đóng Activity không kèm hiệu ứng transition (no animation)
 */

fun Activity.finishNoAnimation() {
    finish()
    overridePendingTransition(0, 0)
}

inline fun <reified T : Activity> Activity.startActivityNoAnimation(
    intentBuilder: Intent.() -> Unit = {}
) {
    val intent = Intent(this, T::class.java)
    intent.intentBuilder()
    startActivity(intent)
    overridePendingTransition(0, 0)
}

inline fun <reified T : Activity> Activity.startActivityAndFinishNoAnimation(
    intentBuilder: Intent.() -> Unit = {}
) {
    val intent = Intent(this, T::class.java)
    intent.intentBuilder()
    startActivity(intent)
    finish()
    overridePendingTransition(0, 0)
}
