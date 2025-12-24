package com.jb.pixelquest.feature.studio.model

/**
 * Studio ?”ë©´???¬ì´???´í™??
 * ?¤ë¹„ê²Œì´?? ?ëŸ¬ ?œì‹œ ??UI ?´ë²¤??
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
 * ?ë””???”ë©´???¬ì´???´í™??
 */
sealed interface EditorSideEffect {
    data class ShowSnackbar(val message: String) : EditorSideEffect
    object NavigateBack : EditorSideEffect
}

