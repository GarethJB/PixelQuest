package com.jb.pixelquest.shared.domain.model.quest

import com.jb.pixelquest.shared.domain.model.reward.Reward

/**
 * 업적 도메인 모델
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

