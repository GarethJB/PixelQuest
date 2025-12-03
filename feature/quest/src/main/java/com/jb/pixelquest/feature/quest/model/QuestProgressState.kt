package com.jb.pixelquest.feature.quest.model

/**
 * Quest 진행 상황 상태
 */
data class QuestProgressState(
    val isLoading: Boolean = false,
    val userProgress: UserQuestProgress = UserQuestProgress(),
    val statistics: QuestStatistics = QuestStatistics(),
    val achievements: List<Achievement> = emptyList(),
    val recentActivity: List<Activity> = emptyList(),
    val earnedRewards: List<Reward> = emptyList(),
    val error: String? = null
)

/**
 * 사용자 퀘스트 진행 상황
 */
data class UserQuestProgress(
    val totalQuestsCompleted: Int = 0,
    val dailyQuestsCompleted: Int = 0,
    val weeklyQuestsCompleted: Int = 0,
    val totalRewardsEarned: Int = 0,
    val currentLevel: Int = 1,
    val experiencePoints: Int = 0,
    val nextLevelExp: Int = 100,
    val streakDays: Int = 0 // 연속 참여 일수
)

/**
 * 퀘스트 통계
 */
data class QuestStatistics(
    val totalArtworksCreated: Int = 0,
    val totalTimeSpent: Long = 0, // 분 단위
    val favoriteTheme: String? = null,
    val mostUsedPalette: String? = null,
    val averageCompletionTime: Long = 0, // 분 단위
    val bestStreak: Int = 0 // 최고 연속 일수
)

/**
 * 업적
 */
data class Achievement(
    val id: String,
    val name: String,
    val description: String,
    val iconPath: String,
    val progress: Int,
    val target: Int,
    val isUnlocked: Boolean,
    val unlockedDate: Long?,
    val reward: Reward?
)

/**
 * 활동 내역
 */
data class Activity(
    val id: String,
    val type: ActivityType, // QUEST_COMPLETED, REWARD_EARNED
    val title: String,
    val description: String,
    val timestamp: Long,
    val relatedId: String? // 관련 퀘스트 ID
)

/**
 * 활동 타입
 */
enum class ActivityType {
    QUEST_COMPLETED, REWARD_EARNED, BADGE_EARNED, LEVEL_UP
}

