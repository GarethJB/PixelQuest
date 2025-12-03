package com.jb.pixelquest.feature.studio.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize

/**
 * Studio 화면의 사용자 액션
 * State Hoisting 패턴을 위해 액션을 명시적으로 정의
 */
sealed interface StudioAction {
    data class SelectRecentWork(val work: RecentWork) : StudioAction
    data class DeleteRecentWork(val workId: String) : StudioAction
    data class SelectTemplate(val template: Template) : StudioAction
    data class SelectCategory(val category: AssetCategory) : StudioAction
    object ShowNewCanvasDialog : StudioAction
    object HideNewCanvasDialog : StudioAction
    object RefreshRecentWorks : StudioAction
    data class ShowError(val message: String) : StudioAction
    object ClearError : StudioAction
}

/**
 * 에디터 화면의 사용자 액션
 */
sealed interface EditorAction {
    data class PixelChanged(val x: Int, val y: Int, val color: Color) : EditorAction
    data class SetSelectedColor(val color: Color) : EditorAction
    data class SetSelectedBrush(val brush: Brush) : EditorAction
    data class SetSelectedPalette(val palette: Palette) : EditorAction
    object ToggleGrid : EditorAction
    data class SetZoom(val zoom: Float) : EditorAction
    data class SetPanOffset(val offset: androidx.compose.ui.geometry.Offset) : EditorAction
    object ClearCanvas : EditorAction
    object Undo : EditorAction
    object Redo : EditorAction
    data class SetDrawingState(val isDrawing: Boolean) : EditorAction
    object SaveCanvas : EditorAction
    object LoadCanvas : EditorAction
}

/**
 * 새 캔버스 다이얼로그의 사용자 액션
 */
sealed interface NewCanvasAction {
    data class SelectSize(val size: CanvasSize) : NewCanvasAction
    data class SetCustomWidth(val width: String) : NewCanvasAction
    data class SetCustomHeight(val height: String) : NewCanvasAction
    data class SetBackgroundColor(val color: Color) : NewCanvasAction
    data class SelectTemplate(val template: Template) : NewCanvasAction
    object ShowSizeDialog : NewCanvasAction
    object HideSizeDialog : NewCanvasAction
    object ShowTemplateSelector : NewCanvasAction
    object HideTemplateSelector : NewCanvasAction
    object CreateCanvas : NewCanvasAction
    object Cancel : NewCanvasAction
}

