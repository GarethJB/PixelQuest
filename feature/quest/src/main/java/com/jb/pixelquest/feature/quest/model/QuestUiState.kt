package com.jb.pixelquest.feature.quest.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize

/**
 * Quest ?”ë©´??UI ?íƒœ
 * State Hoisting ?¨í„´???„í•´ ëª¨ë“  ?íƒœë¥??ìœ„ë¡??Œì–´?¬ë¦¼
 */
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

/**
 * ì±Œë¦°ì§€ ?˜ìŠ¤??
 */
data class ChallengeQuest(
    val id: String,
    val title: String,
    val description: String,
    val theme: String, // "8ë¹„íŠ¸ ë¡œë´‡", "?¬ì´ë²„í‘??ê±°ë¦¬" ??
    val questType: QuestType, // DAILY, WEEKLY
    val difficulty: QuestDifficulty, // EASY, MEDIUM, HARD
    val rewards: List<Reward>,
    val requirements: QuestRequirements,
    val status: QuestStatus, // AVAILABLE, IN_PROGRESS, COMPLETED, LOCKED
    val deadline: Long?, // ì£¼ê°„ ?˜ìŠ¤?¸ì˜ ê²½ìš°
    val thumbnailPath: String?,
    val participantCount: Int = 0, // ì°¸ì—¬????
    val startDate: Long? = null
)

/**
 * ?˜ìŠ¤???€??
 */
enum class QuestType {
    DAILY, WEEKLY
}

/**
 * ?˜ìŠ¤???œì´??
 */
enum class QuestDifficulty {
    EASY, MEDIUM, HARD
}

/**
 * ?˜ìŠ¤???íƒœ
 */
enum class QuestStatus {
    AVAILABLE, IN_PROGRESS, COMPLETED, LOCKED
}

/**
 * ?˜ìŠ¤???”êµ¬?¬í•­
 */
data class QuestRequirements(
    val canvasSize: IntSize? = null,
    val colorLimit: Int? = null, // ?‰ìƒ ?œí•œ
    val themeKeywords: List<String> = emptyList(),
    val minPixels: Int? = null, // ìµœì†Œ ?½ì? ??
    val maxPixels: Int? = null // ìµœë? ?½ì? ??
)

/**
 * ë³´ìƒ
 */
data class Reward(
    val id: String,
    val type: RewardType, // PALETTE, BRUSH, BADGE, ITEM
    val name: String,
    val description: String,
    val iconPath: String?,
    val rarity: RewardRarity = RewardRarity.COMMON
)

/**
 * ë³´ìƒ ?€??
 */
enum class RewardType {
    PALETTE, BRUSH, BADGE, ITEM
}

/**
 * ë³´ìƒ ?¬ê???
 */
enum class RewardRarity {
    COMMON, RARE, EPIC, LEGENDARY
}

/**
 * Quest ??
 */
enum class QuestTab {
    DAILY, WEEKLY, ACTIVE, COMPLETED
}

