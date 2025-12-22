package com.jb.pixelquest.feature.quest.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize

data class QuestUiState(
    val isLoading: Boolean = false,
    val dailyQuests: List<ChallengeQuest> = emptyList(),
    val weeklyQuests: List<ChallengeQuest> = emptyList(),
    val activeQuests: List<ChallengeQuest> = emptyList(),
    val completedQuests: List<ChallengeQuest> = emptyList(),
    val selectedTab: QuestTab = QuestTab.DAILY,
    val selectedQuest: ChallengeQuest? = null,
    val showQuestDetail: Boolean = false,
    val error: String? = null
)

data class ChallengeQuest(
    val id: String,
    val title: String,
    val description: String,
    val theme: String,
    val questType: QuestType, // DAILY, WEEKLY
    val difficulty: QuestDifficulty, // EASY, MEDIUM, HARD
    val rewards: List<Reward>,
    val requirements: QuestRequirements,
    val status: QuestStatus, // AVAILABLE, IN_PROGRESS, COMPLETED, LOCKED
    val deadline: Long?,
    val thumbnailPath: String?,
    val participantCount: Int = 0,
    val startDate: Long? = null
)

enum class QuestType {
    DAILY, WEEKLY
}

enum class QuestDifficulty {
    EASY, MEDIUM, HARD
}

enum class QuestStatus {
    AVAILABLE, IN_PROGRESS, COMPLETED, LOCKED
}

data class QuestRequirements(
    val canvasSize: IntSize? = null,
    val colorLimit: Int? = null,
    val themeKeywords: List<String> = emptyList(),
    val minPixels: Int? = null,
    val maxPixels: Int? = null
)

data class Reward(
    val id: String,
    val type: RewardType, // PALETTE, BRUSH, BADGE, ITEM
    val name: String,
    val description: String,
    val iconPath: String?,
    val rarity: RewardRarity = RewardRarity.COMMON
)

enum class RewardType {
    PALETTE, BRUSH, BADGE, ITEM
}

enum class RewardRarity {
    COMMON, RARE, EPIC, LEGENDARY
}

enum class QuestTab {
    DAILY, WEEKLY, ACTIVE, COMPLETED
}

