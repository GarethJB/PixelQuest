package com.jb.pixelquest.shared.domain.model.quest

/**
 * 활동 타입
 */
enum class ActivityType {
    QUEST_COMPLETED,
    REWARD_EARNED,
    BADGE_EARNED,
    LEVEL_UP
}

/**
 * 활동 도메인 모델
 */
data class Activity(
    val id: String,
    val type: ActivityType,
    val title: String,
    val description: String,
    val timestamp: Long,
    val relatedId: String?
)

