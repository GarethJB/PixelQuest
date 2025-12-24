package com.jb.pixelquest.feature.studio.model

import androidx.compose.ui.geometry.Offset

data class CanvasInteractionState(
    val lastPixelPosition: PixelPosition? = null,
    val isDragging: Boolean = false,
    val dragStartPosition: Offset? = null,
    val currentDragPosition: Offset? = null
)

data class PixelPosition(
    val x: Int,
    val y: Int
)

