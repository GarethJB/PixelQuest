package com.jb.pixelquest.feature.studio.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize

/**
 * Studio ?�면??UI ?�태
 * State Hoisting ?�턴???�해 모든 ?�태�??�위�??�어?�림
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
    val error: String? = null,
    val showEditor: Boolean = false
)

/**
 * 최근 ?�업 ??��
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
 * ?�플�?
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
 * ?�플�?카테고리
 */
enum class TemplateCategory {
    CHARACTER,
    LANDSCAPE,
    OBJECT,
    ICON,
    PATTERN
}

/**
 * ?�레??
 */
data class Palette(
    val id: String,
    val name: String,
    val colors: List<Color>,
    val isDefault: Boolean
)

/**
 * 브러??
 */
data class Brush(
    val id: String,
    val name: String,
    val size: Int,
    val shape: BrushShape,
    val previewImagePath: String?
)

/**
 * 브러??모양
 */
enum class BrushShape {
    CIRCLE,
    SQUARE,
    DIAMOND
}

/**
 * ?�셋 카테고리
 */
enum class AssetCategory {
    TEMPLATE,
    PALETTE,
    BRUSH
}

