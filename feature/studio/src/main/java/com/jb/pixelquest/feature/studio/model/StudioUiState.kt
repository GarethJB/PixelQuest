package com.jb.pixelquest.feature.studio.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize

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

data class RecentWork(
    val id: String,
    val name: String,
    val thumbnailPath: String?,
    val lastModified: Long,
    val filePath: String,
    val canvasSize: IntSize
)

data class Template(
    val id: String,
    val name: String,
    val category: TemplateCategory,
    val thumbnailPath: String,
    val canvasSize: IntSize,
    val previewImagePath: String?
)

enum class TemplateCategory {
    CHARACTER,
    LANDSCAPE,
    OBJECT,
    ICON,
    PATTERN
}

data class Palette(
    val id: String,
    val name: String,
    val colors: List<Color>,
    val isDefault: Boolean
)

data class Brush(
    val id: String,
    val name: String,
    val size: Int,
    val shape: BrushShape,
    val previewImagePath: String?
)

enum class BrushShape {
    CIRCLE,
    SQUARE,
    DIAMOND
}

enum class AssetCategory {
    TEMPLATE,
    PALETTE,
    BRUSH
}