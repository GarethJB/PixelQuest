package com.jb.pixelquest.feature.quest.model

/**
 * Quest ì§„í–‰ ?í™© ?íƒœ
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
 * ?¬ìš©???˜ìŠ¤??ì§„í–‰ ?í™©
 */
data class UserQuestProgress(
    val totalQuestsCompleted: Int = 0,
    val dailyQuestsCompleted: Int = 0,
    val weeklyQuestsCompleted: Int = 0,
    val totalRewardsEarned: Int = 0,
    val currentLevel: Int = 1,
    val experiencePoints: Int = 0,
    val nextLevelExp: Int = 100,
    val streakDays: Int = 0 // ?°ì† ì°¸ì—¬ ?¼ìˆ˜
)

/**
 * ?˜ìŠ¤???µê³„
 */
data class QuestStatistics(
    val totalArtworksCreated: Int = 0,
    val totalTimeSpent: Long = 0, // ë¶??¨ìœ„
    val favoriteTheme: String? = null,
    val mostUsedPalette: String? = null,
    val averageCompletionTime: Long = 0, // ë¶??¨ìœ„
    val bestStreak: Int = 0 // ìµœê³  ?°ì† ?¼ìˆ˜
)

/**
 * ?…ì 
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
 * ?œë™ ?´ì—­
 */
data class Activity(
    val id: String,
    val type: ActivityType, // QUEST_COMPLETED, REWARD_EARNED
    val title: String,
    val description: String,
    val timestamp: Long,
    val relatedId: String? // ê´€???˜ìŠ¤??ID
)

/**
 * ?œë™ ?€??
 */
enum class ActivityType {
    QUEST_COMPLETED, REWARD_EARNED, BADGE_EARNED, LEVEL_UP
}

