package com.jb.pixelquest.shared.domain.model.quest

/**
 * 퀘스트 통계
 */
data class QuestStatistics(
    val totalArtworksCreated: Int = 0,
    val totalTimeSpent: Long = 0,
    val favoriteTheme: String? = null,
    val mostUsedPalette: String? = null,
    val averageCompletionTime: Long = 0,
    val bestStreak: Int = 0
)

