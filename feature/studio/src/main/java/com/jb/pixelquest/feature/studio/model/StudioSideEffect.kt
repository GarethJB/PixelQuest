package com.jb.pixelquest.feature.studio.model

/**
 * Studio 화면의 사이드 이펙트
 * 네비게이션, 에러 표시 등 UI 이벤트
 */
sealed interface StudioSideEffect {
    data class NavigateToEditor(
        val canvasId: String? = null,
        val canvasSize: androidx.compose.ui.unit.IntSize? = null,
        val backgroundColor: androidx.compose.ui.graphics.Color? = null,
        val template: Template? = null
    ) : StudioSideEffect
    data class ShowSnackbar(val message: String) : StudioSideEffect
}

/**
 * 에디터 화면의 사이드 이펙트
 */
sealed interface EditorSideEffect {
    data class ShowSnackbar(val message: String) : EditorSideEffect
    object NavigateBack : EditorSideEffect
}

