package com.jb.pixelquest.shared.domain.model.quest

import com.jb.pixelquest.shared.domain.model.common.CanvasSize

/**
 * 퀘스트 요구사항
 */
data class QuestRequirements(
    val canvasSize: CanvasSize? = null,
    val colorLimit: Int? = null,
    val themeKeywords: List<String> = emptyList(),
    val minPixels: Int? = null,
    val maxPixels: Int? = null
)

