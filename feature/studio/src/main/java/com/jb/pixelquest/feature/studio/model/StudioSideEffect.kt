package com.jb.pixelquest.feature.studio.model

sealed interface StudioSideEffect {
    data class NavigateToEditor(
        val canvasId: String? = null,
        val canvasSize: androidx.compose.ui.unit.IntSize? = null,
        val backgroundColor: androidx.compose.ui.graphics.Color? = null,
        val template: Template? = null
    ) : StudioSideEffect
    data class ShowSnackbar(val message: String) : StudioSideEffect
}

sealed interface EditorSideEffect {
    data class ShowSnackbar(val message: String) : EditorSideEffect
    object NavigateBack : EditorSideEffect
}

