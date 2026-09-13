package com.odrixon.yadoviet.ui.screens

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Size
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.odrixon.yadoviet.R
import com.odrixon.yadoviet.ui.components.QrScannerBoundingBox
import com.odrixon.yadoviet.utils.QrCodeAnalyzer
import java.util.concurrent.Executors

@Composable
fun QrScannerScreen(
    onQrScanned: (String) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val view = LocalView.current
    val lifecycleOwner = LocalLifecycleOwner.current

    // Make status bar transparent with light icons (white) when QR scanner is open, restore on dispose
    DisposableEffect(Unit) {
        val window = (view.context as? Activity)?.window
        val insetsController = window?.let { WindowCompat.getInsetsController(it, view) }
        val prevStatusBarColor = window?.statusBarColor
        val prevLightStatusBar = insetsController?.isAppearanceLightStatusBars

        if (window != null) {
            window.statusBarColor = android.graphics.Color.TRANSPARENT
            insetsController?.isAppearanceLightStatusBars = false
        }

        onDispose {
            if (window != null && prevStatusBarColor != null) {
                window.statusBarColor = prevStatusBarColor
                insetsController?.isAppearanceLightStatusBars = prevLightStatusBar ?: true
            }
        }
    }

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCameraPermission = granted
        }
    )

    LaunchedEffect(Unit) {
        if (!hasCameraPermission) {
            launcher.launch(Manifest.permission.CAMERA)
        }
    }

    BackHandler {
        onClose()
    }

    var isFlashOn by remember { mutableStateOf(false) }
    var currentZoomRatio by remember { mutableFloatStateOf(1f) }
    var camera by remember { mutableStateOf<Camera?>(null) }
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }

    DisposableEffect(Unit) {
        onDispose {
            cameraExecutor.shutdown()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .pointerInput(camera) {
                detectTransformGestures { _, _, zoom, _ ->
                    val cam = camera ?: return@detectTransformGestures
                    val zoomState = cam.cameraInfo.zoomState.value ?: return@detectTransformGestures
                    val minZoom = zoomState.minZoomRatio
                    val maxZoom = zoomState.maxZoomRatio
                    val currentRatio = zoomState.zoomRatio
                    val targetRatio = (currentRatio * zoom).coerceIn(minZoom, maxZoom)
                    cam.cameraControl.setZoomRatio(targetRatio)
                    currentZoomRatio = targetRatio
                }
            }
    ) {
        if (hasCameraPermission) {
            // Camera Live Preview
            AndroidView(
                factory = { ctx ->
                    val previewView = PreviewView(ctx)
                    val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)

                    cameraProviderFuture.addListener({
                        val cameraProvider = cameraProviderFuture.get()

                        val preview = Preview.Builder().build().also {
                            it.setSurfaceProvider(previewView.surfaceProvider)
                        }

                        val imageAnalysis = ImageAnalysis.Builder()
                            .setTargetResolution(Size(1280, 720))
                            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                            .build()
                            .also {
                                it.setAnalyzer(
                                    cameraExecutor,
                                    QrCodeAnalyzer { result ->
                                        onQrScanned(result)
                                    }
                                )
                            }

                        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                        try {
                            cameraProvider.unbindAll()
                            val boundCamera = cameraProvider.bindToLifecycle(
                                lifecycleOwner,
                                cameraSelector,
                                preview,
                                imageAnalysis
                            )
                            camera = boundCamera

                            // Observe camera zoom state updates (e.g. from pinch or tap)
                            boundCamera.cameraInfo.zoomState.observe(lifecycleOwner) { state ->
                                if (state != null) {
                                    currentZoomRatio = state.zoomRatio
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }, ContextCompat.getMainExecutor(ctx))

                    previewView
                },
                modifier = Modifier.fillMaxSize()
            )

            // Central Bounding Box Overlay (Custom Canvas matching user's image)
            QrScannerBoundingBox(
                boxSize = 270.dp,
                cornerLength = 36.dp,
                cornerStrokeWidth = 4.5.dp,
                cornerRadius = 22.dp,
                boxColor = Color.White
            )

            // Top Control Bar: [X Close] --- [Capsule 1x / 2x Zoom] --- [Flash Toggle]
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 1. Close Button (X)
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.35f))
                        .clickable { onClose() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "✕",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // 2. Center Zoom Capsule ([Camera Icon] 1x / 2x) matching screenshot
                val zoomText = if (currentZoomRatio >= 1.95f) {
                    "%.1fx".format(currentZoomRatio).replace(".0x", "x")
                } else if (currentZoomRatio <= 1.05f) {
                    "1x"
                } else {
                    "%.1fx".format(currentZoomRatio)
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color.Black.copy(alpha = 0.45f))
                        .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(24.dp))
                        .clickable {
                            val cam = camera ?: return@clickable
                            val zoomState = cam.cameraInfo.zoomState.value
                            val minZoom = zoomState?.minZoomRatio ?: 1f
                            val maxZoom = zoomState?.maxZoomRatio ?: 4f
                            
                            // Toggle between 1x and 2x (or max zoom if < 2x)
                            if (currentZoomRatio < 1.8f) {
                                val target = 2f.coerceIn(minZoom, maxZoom)
                                cam.cameraControl.setZoomRatio(target)
                            } else {
                                cam.cameraControl.setZoomRatio(minZoom)
                            }
                        }
                        .padding(horizontal = 14.dp, vertical = 7.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_camera_outlined),
                            contentDescription = "Zoom",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = zoomText,
                            color = Color.White,
                            fontSize = 13.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // 3. Flash Toggle Button (Flash on / off)
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(if (isFlashOn) Color.White else Color.Black.copy(alpha = 0.35f))
                        .clickable {
                            isFlashOn = !isFlashOn
                            camera?.cameraControl?.enableTorch(isFlashOn)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isFlashOn) R.drawable.ic_flash_on else R.drawable.ic_flash_off
                        ),
                        contentDescription = "Flash",
                        tint = if (isFlashOn) Color.Black else Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Bottom Instruction Text
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 60.dp, start = 30.dp, end = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Di chuyển camera đến mã QR / Mã phòng",
                    color = Color.White,
                    fontSize = 14.5.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Khung quét tự động nhận diện hóa đơn, phòng trọ và thiết bị",
                    color = Color.White.copy(alpha = 0.75f),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }

        } else {
            // Permission Request Fallback UI
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Cần quyền truy cập Camera",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Vui lòng cấp quyền máy ảnh để sử dụng tính năng quét mã QR phòng trọ và hóa đơn.",
                    color = Color(0xFF94A3B8),
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF032C95))
                        .clickable { launcher.launch(Manifest.permission.CAMERA) }
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = "Cấp quyền Camera",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Đóng",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 13.sp,
                    modifier = Modifier.clickable { onClose() }
                )
            }
        }
    }
}
