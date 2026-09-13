package com.odrixon.yadoviet.ui.screens

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.odrixon.yadoviet.ui.theme.BrandPrimary
import com.odrixon.yadoviet.ui.theme.TextPrimary
import com.odrixon.yadoviet.ui.theme.TextSecondary

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebManageScreen(
    initialUrl: String,
    title: String = "YadoViet Quản lý",
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var webView: WebView? by remember { mutableStateOf(null) }
    var progress by remember { mutableFloatStateOf(0f) }
    var isLoading by remember { mutableStateOf(true) }
    var showMenu by remember { mutableStateOf(false) }
    var currentUrl by remember { mutableStateOf(initialUrl) }
    var hasError by remember { mutableStateOf(false) }

    val customUserAgent = "Mozilla/5.0 (Linux; Android 13; SM-G998B Build/TP1A.220624.014; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/115.0.5790.166 Mobile Safari/537.36 YadoVietApp/8.10.0 YadoVietTheme/light"

    BackHandler(enabled = true) {
        if (webView?.canGoBack() == true) {
            webView?.goBack()
        } else {
            onCloseClick()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        // Main Fullscreen WebView
        AndroidView(
            factory = { ctx ->
                WebView(ctx).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )

                    settings.apply {
                        javaScriptEnabled = true
                        domStorageEnabled = true
                        useWideViewPort = true
                        loadWithOverviewMode = true
                        setSupportZoom(true)
                        builtInZoomControls = true
                        displayZoomControls = false
                        cacheMode = WebSettings.LOAD_DEFAULT
                        userAgentString = customUserAgent
                        mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                        allowFileAccess = true
                        allowContentAccess = true
                    }

                    val webViewInstance = this
                    CookieManager.getInstance().apply {
                        setAcceptCookie(true)
                        setAcceptThirdPartyCookies(webViewInstance, true)
                    }

                    // JavaScript Bridge to receive instant loading state & retry action
                    addJavascriptInterface(object {
                        @JavascriptInterface
                        fun onLoadingStateChange(state: String) {
                            post {
                                if (state == "web_ready" || state == "loading_finish") {
                                    isLoading = false
                                } else if (state == "loading_start") {
                                    isLoading = true
                                }
                            }
                        }

                        @JavascriptInterface
                        fun reloadPage() {
                            post {
                                hasError = false
                                isLoading = true
                                loadUrl(initialUrl)
                            }
                        }
                    }, "YadoVietNative")

                    webChromeClient = object : WebChromeClient() {
                        override fun onProgressChanged(view: WebView?, newProgress: Int) {
                            progress = newProgress / 100f
                            if (newProgress >= 100) {
                                // Fallback delay if JS bridge didn't fire
                                postDelayed({
                                    isLoading = false
                                }, 800)
                            }
                        }
                    }

                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                            if (url?.startsWith("file:///android_asset") != true) {
                                isLoading = true
                                hasError = false
                                url?.let { currentUrl = it }
                            }
                        }

                        override fun onPageFinished(view: WebView?, url: String?) {
                            if (url?.startsWith("file:///android_asset") != true) {
                                url?.let { currentUrl = it }
                                postDelayed({
                                    isLoading = false
                                }, 800)
                            } else {
                                isLoading = false
                            }
                        }

                        override fun onReceivedError(
                            view: WebView?,
                            request: WebResourceRequest?,
                            error: WebResourceError?
                        ) {
                            if (request?.isForMainFrame == true) {
                                isLoading = false
                                hasError = true
                                // Load offline.html asset directly into the WebView
                                view?.loadUrl("file:///android_asset/offline.html")
                            }
                        }

                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            request: WebResourceRequest?
                        ): Boolean {
                            return false
                        }
                    }

                    loadUrl(initialUrl)
                    webView = this
                }
            },
            update = {
                // Update if needed
            },
            modifier = Modifier.fillMaxSize()
        )

        // Asset HTML Loading Overlay Layer (Đồng bộ 100% Web engine WebView)
        AnimatedVisibility(
            visible = isLoading && !hasError,
            enter = androidx.compose.animation.EnterTransition.None,
            exit = fadeOut(animationSpec = androidx.compose.animation.core.tween(durationMillis = 300))
        ) {
            AndroidView(
                factory = { ctx ->
                    WebView(ctx).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        settings.apply {
                            javaScriptEnabled = true
                            domStorageEnabled = true
                            useWideViewPort = true
                            loadWithOverviewMode = true
                            allowFileAccess = true
                            userAgentString = customUserAgent
                        }
                        setBackgroundColor(android.graphics.Color.WHITE)
                        loadUrl("file:///android_asset/loading.html")
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        // Floating Capsule Control Bar directly OVER the WebView (Top Right)
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 12.dp, end = 14.dp)
                .shadow(
                    elevation = 6.dp,
                    shape = RoundedCornerShape(24.dp),
                    ambientColor = Color.Black.copy(alpha = 0.08f),
                    spotColor = Color.Black.copy(alpha = 0.14f)
                )
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White.copy(alpha = 0.95f))
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(24.dp))
                .padding(horizontal = 4.dp, vertical = 2.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Menu Button (...)
                IconButton(
                    onClick = { showMenu = true },
                    modifier = Modifier.size(32.dp)
                ) {
                    Text(
                        text = "•••",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFF1E293B),
                        letterSpacing = (-1).sp
                    )
                }

                // Vertical Divider Line
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(14.dp)
                        .background(Color(0xFFCBD5E1))
                )

                // Close Button (X)
                IconButton(
                    onClick = onCloseClick,
                    modifier = Modifier.size(32.dp)
                ) {
                    Text(
                        text = "✕",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B)
                    )
                }
            }

            // Dropdown Menu for "..."
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false },
                modifier = Modifier.background(Color.White)
            ) {
                DropdownMenuItem(
                    text = { Text("Tải lại trang", fontSize = 14.sp, fontWeight = FontWeight.Medium) },
                    onClick = {
                        showMenu = false
                        hasError = false
                        webView?.reload()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Sao chép liên kết", fontSize = 14.sp, fontWeight = FontWeight.Medium) },
                    onClick = {
                        showMenu = false
                        val clipboard = context.getSystemService(android.content.Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                        val clip = android.content.ClipData.newPlainText("URL", currentUrl)
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(context, "Đã sao chép liên kết", Toast.LENGTH_SHORT).show()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Đóng", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFFEF4444)) },
                    onClick = {
                        showMenu = false
                        onCloseClick()
                    }
                )
            }
        }
    }
}
