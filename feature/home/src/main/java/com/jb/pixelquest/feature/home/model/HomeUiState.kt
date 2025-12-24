package com.jb.pixelquest.feature.home.model

/**
 * ???”ë©´??UI ?íƒœ ?•ë³´
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


