package com.jb.pixelquest.feature.home.model

import androidx.compose.ui.unit.IntSize

data class HomeUiState(
    val welcomeTitle: String,
    val welcomeMessage: String,
    val highlights: List<HomeHighlight>,
    val workshop: Workshop = Workshop(),
    val canvases: List<Canvas> = emptyList()
)

data class HomeHighlight(
    val title: String,
    val description: String
)

data class Workshop(
    val id: String = "default",
    val name: String = "My Workshop",
    val decorationLevel: Int = 1,
    val theme: WorkshopTheme = WorkshopTheme.DEFAULT
)

enum class WorkshopTheme {
    DEFAULT,
    MODERN,
    CLASSIC,
    RUSTIC
}

data class Canvas(
    val id: String,
    val name: String,
    val thumbnailPath: String? = null,
    val lastModified: Long,
    val canvasSize: IntSize
)
