package com.jb.pixelquest.shared.domain.model.quest

import com.jb.pixelquest.shared.domain.model.reward.Reward

/**
 * 퀘스트 도메인 모델
 */
data class Quest(
    val id: String,
    val title: String,
    val description: String,
    val theme: String,
    val questType: QuestType,
    val difficulty: QuestDifficulty,
    val rewards: List<Reward>,
    val requirements: QuestRequirements,
    val status: QuestStatus,
    val deadline: Long?,
    val thumbnailPath: String?,
    val participantCount: Int = 0,
    val startDate: Long? = null
)

