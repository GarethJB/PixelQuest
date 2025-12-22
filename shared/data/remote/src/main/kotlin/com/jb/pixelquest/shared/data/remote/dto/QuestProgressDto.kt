package com.jb.pixelquest.shared.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * 사용자 퀘스트 진행도 DTO
 */
data class UserQuestProgressDto(
    @SerializedName("totalQuestsCompleted")
    val totalQuestsCompleted: Int = 0,
    @SerializedName("dailyQuestsCompleted")
    val dailyQuestsCompleted: Int = 0,
    @SerializedName("weeklyQuestsCompleted")
    val weeklyQuestsCompleted: Int = 0,
    @SerializedName("totalRewardsEarned")
    val totalRewardsEarned: Int = 0,
    @SerializedName("currentLevel")
    val currentLevel: Int = 1,
    @SerializedName("experiencePoints")
    val experiencePoints: Int = 0,
    @SerializedName("nextLevelExp")
    val nextLevelExp: Int = 100,
    @SerializedName("streakDays")
    val streakDays: Int = 0
)

/**
 * 퀘스트 통계 DTO
 */
data class QuestStatisticsDto(
    @SerializedName("totalArtworksCreated")
    val totalArtworksCreated: Int = 0,
    @SerializedName("totalTimeSpent")
    val totalTimeSpent: Long = 0,
    @SerializedName("favoriteTheme")
    val favoriteTheme: String? = null,
    @SerializedName("mostUsedPalette")
    val mostUsedPalette: String? = null,
    @SerializedName("averageCompletionTime")
    val averageCompletionTime: Long = 0,
    @SerializedName("bestStreak")
    val bestStreak: Int = 0
)

/**
 * 업적 DTO
 */
data class AchievementDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("iconPath")
    val iconPath: String,
    @SerializedName("progress")
    val progress: Int,
    @SerializedName("target")
    val target: Int,
    @SerializedName("isUnlocked")
    val isUnlocked: Boolean,
    @SerializedName("unlockedDate")
    val unlockedDate: Long?,
    @SerializedName("reward")
    val reward: RewardDto?
)

/**
 * 활동 DTO
 */
data class ActivityDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String, // "QUEST_COMPLETED", "REWARD_EARNED", "BADGE_EARNED", "LEVEL_UP"
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("relatedId")
    val relatedId: String?
)

