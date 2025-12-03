package com.jb.pixelquest.feature.studio.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize

/**
 * Studio 화면의 UI 상태
 * State Hoisting 패턴을 위해 모든 상태를 상위로 끌어올림
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
 * 최근 작업 항목
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
 * 템플릿
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
 * 템플릿 카테고리
 */
enum class TemplateCategory {
    CHARACTER,
    LANDSCAPE,
    OBJECT,
    ICON,
    PATTERN
}

/**
 * 팔레트
 */
data class Palette(
    val id: String,
    val name: String,
    val colors: List<Color>,
    val isDefault: Boolean
)

/**
 * 브러시
 */
data class Brush(
    val id: String,
    val name: String,
    val size: Int,
    val shape: BrushShape,
    val previewImagePath: String?
)

/**
 * 브러시 모양
 */
enum class BrushShape {
    CIRCLE,
    SQUARE,
    DIAMOND
}

/**
 * 에셋 카테고리
 */
enum class AssetCategory {
    TEMPLATE,
    PALETTE,
    BRUSH
}

