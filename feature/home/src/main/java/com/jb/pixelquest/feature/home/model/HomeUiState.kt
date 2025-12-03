package com.jb.pixelquest.feature.home.model

/**
 * 홈 화면의 UI 상태 정보
 */
data class HomeUiState(
    val welcomeTitle: String,
    val welcomeMessage: String,
    val highlights: List<HomeHighlight>
)

data class HomeHighlight(
    val title: String,
    val description: String
)


