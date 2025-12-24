package com.jb.pixelquest.feature.studio.model

import androidx.compose.ui.geometry.Offset

/**
 * ìº”ë²„???í˜¸?‘ìš© ?íƒœ
 * State Hoisting ?¨í„´???„í•´ ?°ì¹˜/?œìŠ¤ì²?ê´€???íƒœë¥??ìœ„ë¡??Œì–´?¬ë¦¼
 */
data class CanvasInteractionState(
    val lastPixelPosition: PixelPosition? = null,
    val isDragging: Boolean = false,
    val dragStartPosition: Offset? = null,
    val currentDragPosition: Offset? = null
)

/**
 * ?½ì? ?„ì¹˜
 */
data class PixelPosition(
    val x: Int,
    val y: Int
)

