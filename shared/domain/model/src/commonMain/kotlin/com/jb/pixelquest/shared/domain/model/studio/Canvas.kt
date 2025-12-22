package com.jb.pixelquest.shared.domain.model.studio

import com.jb.pixelquest.shared.domain.model.common.CanvasSize

/**
 * 캔버스 도메인 모델
 */
data class Canvas(
    val id: String,
    val name: String,
    val thumbnailPath: String? = null,
    val lastModified: Long,
    val canvasSize: CanvasSize,
    val filePath: String? = null
)

