package com.jb.pixelquest.shared.domain.model.studio

import com.jb.pixelquest.shared.domain.model.common.Color

/**
 * 팔레트 도메인 모델
 */
data class Palette(
    val id: String,
    val name: String,
    val colors: List<Color>,
    val isDefault: Boolean = false
)

