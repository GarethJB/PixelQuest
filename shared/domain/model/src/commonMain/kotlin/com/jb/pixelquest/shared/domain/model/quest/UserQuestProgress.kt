package com.jb.pixelquest.shared.domain.model.quest

/**
 * 사용자 퀘스트 진행도
 */
data class UserQuestProgress(
    val totalQuestsCompleted: Int = 0,
    val dailyQuestsCompleted: Int = 0,
    val weeklyQuestsCompleted: Int = 0,
    val totalRewardsEarned: Int = 0,
    val currentLevel: Int = 1,
    val experiencePoints: Int = 0,
    val nextLevelExp: Int = 100,
    val streakDays: Int = 0
)

