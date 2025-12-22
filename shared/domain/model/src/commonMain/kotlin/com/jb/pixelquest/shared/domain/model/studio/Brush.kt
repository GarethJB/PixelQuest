package com.jb.pixelquest.shared.domain.model.studio

/**
 * 브러시 모양
 */
enum class BrushShape {
    CIRCLE,
    SQUARE,
    DIAMOND
}

/**
 * 브러시 도메인 모델
 */
data class Brush(
    val id: String,
    val name: String,
    val size: Int,
    val shape: BrushShape,
    val previewImagePath: String?
)

