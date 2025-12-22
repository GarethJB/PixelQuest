package com.jb.pixelquest.shared.domain.model.common

/**
 * 캔버스 크기를 나타내는 도메인 모델
 * UI 의존성 없이 순수 Kotlin으로 정의
 */
data class CanvasSize(
    val width: Int,
    val height: Int
) {
    val pixelCount: Int
        get() = width * height
}

