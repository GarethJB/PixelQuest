package com.jb.pixelquest.feature.quest.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize

/**
 * Quest 화면의 UI 상태
 * State Hoisting 패턴을 위해 모든 상태를 상위로 끌어올림
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
 * 챌린지 퀘스트
 */
data class ChallengeQuest(
    val id: String,
    val title: String,
    val description: String,
    val theme: String, // "8비트 로봇", "사이버펑크 거리" 등
    val questType: QuestType, // DAILY, WEEKLY
    val difficulty: QuestDifficulty, // EASY, MEDIUM, HARD
    val rewards: List<Reward>,
    val requirements: QuestRequirements,
    val status: QuestStatus, // AVAILABLE, IN_PROGRESS, COMPLETED, LOCKED
    val deadline: Long?, // 주간 퀘스트의 경우
    val thumbnailPath: String?,
    val participantCount: Int = 0, // 참여자 수
    val startDate: Long? = null
)

/**
 * 퀘스트 타입
 */
enum class QuestType {
    DAILY, WEEKLY
}

/**
 * 퀘스트 난이도
 */
enum class QuestDifficulty {
    EASY, MEDIUM, HARD
}

/**
 * 퀘스트 상태
 */
enum class QuestStatus {
    AVAILABLE, IN_PROGRESS, COMPLETED, LOCKED
}

/**
 * 퀘스트 요구사항
 */
data class QuestRequirements(
    val canvasSize: IntSize? = null,
    val colorLimit: Int? = null, // 색상 제한
    val themeKeywords: List<String> = emptyList(),
    val minPixels: Int? = null, // 최소 픽셀 수
    val maxPixels: Int? = null // 최대 픽셀 수
)

/**
 * 보상
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
 * 보상 타입
 */
enum class RewardType {
    PALETTE, BRUSH, BADGE, ITEM
}

/**
 * 보상 희귀도
 */
enum class RewardRarity {
    COMMON, RARE, EPIC, LEGENDARY
}

/**
 * Quest 탭
 */
enum class QuestTab {
    DAILY, WEEKLY, ACTIVE, COMPLETED
}

