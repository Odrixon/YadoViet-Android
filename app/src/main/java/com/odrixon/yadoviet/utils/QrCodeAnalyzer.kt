package com.odrixon.yadoviet.utils

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.zxing.BinaryBitmap
import com.google.zxing.DecodeHintType
import com.google.zxing.MultiFormatReader
import com.google.zxing.PlanarYUVLuminanceSource
import com.google.zxing.common.HybridBinarizer
import java.nio.ByteBuffer

class QrCodeAnalyzer(
    private val onQrCodeScanned: (String) -> Unit
) : ImageAnalysis.Analyzer {

    private val reader = MultiFormatReader().apply {
        val hints = mapOf(
            DecodeHintType.POSSIBLE_FORMATS to listOf(
                com.google.zxing.BarcodeFormat.QR_CODE,
                com.google.zxing.BarcodeFormat.CODE_128,
                com.google.zxing.BarcodeFormat.EAN_13
            ),
            DecodeHintType.TRY_HARDER to true
        )
        setHints(hints)
    }

    private var isScanning = true

    override fun analyze(image: ImageProxy) {
        if (!isScanning) {
            image.close()
            return
        }

        val buffer: ByteBuffer = image.planes[0].buffer
        val data = ByteArray(buffer.remaining())
        buffer.get(data)

        val width = image.width
        val height = image.height

        val source = PlanarYUVLuminanceSource(
            data,
            width,
            height,
            0,
            0,
            width,
            height,
            false
        )

        val binaryBitmap = BinaryBitmap(HybridBinarizer(source))

        try {
            val result = reader.decodeWithState(binaryBitmap)
            if (result != null && result.text.isNotEmpty()) {
                isScanning = false
                onQrCodeScanned(result.text)
            }
        } catch (e: Exception) {
            // QR not detected in current frame
        } finally {
            reader.reset()
            image.close()
        }
    }

    fun resumeScanning() {
        isScanning = true
    }

    fun pauseScanning() {
        isScanning = false
    }
}
