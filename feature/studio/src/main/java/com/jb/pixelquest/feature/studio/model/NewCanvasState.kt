package com.jb.pixelquest.feature.studio.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize

data class NewCanvasState(
    val selectedSize: CanvasSize = CanvasSize.SIZE_32X32,
    val customWidth: String = "",
    val customHeight: String = "",
    val backgroundColor: Color = Color.White,
    val selectedTemplate: Template? = null,
    val showSizeDialog: Boolean = false,
    val showTemplateSelector: Boolean = false
)

enum class CanvasSize(val size: IntSize, val displayName: String) {
    SIZE_16X16(IntSize(16, 16), "16x16"),
    SIZE_32X32(IntSize(32, 32), "32x32"),
    SIZE_64X64(IntSize(64, 64), "64x64"),
    SIZE_128X128(IntSize(128, 128), "128x128"),
    CUSTOM(IntSize(0, 0), "Custom")
}

