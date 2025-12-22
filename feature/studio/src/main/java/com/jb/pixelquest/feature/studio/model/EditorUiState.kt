package com.jb.pixelquest.feature.studio.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize

data class EditorUiState(
    val canvasSize: IntSize,
    val pixels: Array<Array<Color>>,
    val selectedColor: Color,
    val showGrid: Boolean,
    val zoom: Float,
    val panOffset: Offset,
    val selectedBrush: Brush? = null,
    val selectedPalette: Palette? = null,
    val isDrawing: Boolean = false,
    val canUndo: Boolean = false,
    val canRedo: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EditorUiState

        if (canvasSize != other.canvasSize) return false
        if (!pixels.contentDeepEquals(other.pixels)) return false
        if (selectedColor != other.selectedColor) return false
        if (showGrid != other.showGrid) return false
        if (zoom != other.zoom) return false
        if (panOffset != other.panOffset) return false
        if (selectedBrush != other.selectedBrush) return false
        if (selectedPalette != other.selectedPalette) return false
        if (isDrawing != other.isDrawing) return false
        if (canUndo != other.canUndo) return false
        if (canRedo != other.canRedo) return false

        return true
    }

    override fun hashCode(): Int {
        var result = canvasSize.hashCode()
        result = 31 * result + pixels.contentDeepHashCode()
        result = 31 * result + selectedColor.hashCode()
        result = 31 * result + showGrid.hashCode()
        result = 31 * result + zoom.hashCode()
        result = 31 * result + panOffset.hashCode()
        result = 31 * result + (selectedBrush?.hashCode() ?: 0)
        result = 31 * result + (selectedPalette?.hashCode() ?: 0)
        result = 31 * result + isDrawing.hashCode()
        result = 31 * result + canUndo.hashCode()
        result = 31 * result + canRedo.hashCode()
        return result
    }
}

