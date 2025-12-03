package com.jb.pixelquest.feature.studio.model

import androidx.compose.ui.geometry.Offset

/**
 * 캔버스 상호작용 상태
 * State Hoisting 패턴을 위해 터치/제스처 관련 상태를 상위로 끌어올림
 */
data class CanvasInteractionState(
    val lastPixelPosition: PixelPosition? = null,
    val isDragging: Boolean = false,
    val dragStartPosition: Offset? = null,
    val currentDragPosition: Offset? = null
)

/**
 * 픽셀 위치
 */
data class PixelPosition(
    val x: Int,
    val y: Int
)

