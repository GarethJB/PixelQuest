package com.jb.pixelquest.feature.studio.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize

/**
 * Studio ?”ë©´??UI ?íƒœ
 * State Hoisting ?¨í„´???„í•´ ëª¨ë“  ?íƒœë¥??ìœ„ë¡??Œì–´?¬ë¦¼
 */
data class StudioUiState(
    val isLoading: Boolean = false,
    val recentWorks: List<RecentWork> = emptyList(),
    val templates: List<Template> = emptyList(),
    val palettes: List<Palette> = emptyList(),
    val brushes: List<Brush> = emptyList(),
    val selectedCategory: AssetCategory? = null,
    val showNewCanvasDialog: Boolean = false,
    val newCanvasState: NewCanvasState = NewCanvasState(),
    val error: String? = null
)

/**
 * ìµœê·¼ ?‘ì—… ??ª©
 */
data class RecentWork(
    val id: String,
    val name: String,
    val thumbnailPath: String?,
    val lastModified: Long,
    val filePath: String,
    val canvasSize: IntSize
)

/**
 * ?œí”Œë¦?
 */
data class Template(
    val id: String,
    val name: String,
    val category: TemplateCategory,
    val thumbnailPath: String,
    val canvasSize: IntSize,
    val previewImagePath: String?
)

/**
 * ?œí”Œë¦?ì¹´í…Œê³ ë¦¬
 */
enum class TemplateCategory {
    CHARACTER,
    LANDSCAPE,
    OBJECT,
    ICON,
    PATTERN
}

/**
 * ?”ë ˆ??
 */
data class Palette(
    val id: String,
    val name: String,
    val colors: List<Color>,
    val isDefault: Boolean
)

/**
 * ë¸ŒëŸ¬??
 */
data class Brush(
    val id: String,
    val name: String,
    val size: Int,
    val shape: BrushShape,
    val previewImagePath: String?
)

/**
 * ë¸ŒëŸ¬??ëª¨ì–‘
 */
enum class BrushShape {
    CIRCLE,
    SQUARE,
    DIAMOND
}

/**
 * ?ì…‹ ì¹´í…Œê³ ë¦¬
 */
enum class AssetCategory {
    TEMPLATE,
    PALETTE,
    BRUSH
}

