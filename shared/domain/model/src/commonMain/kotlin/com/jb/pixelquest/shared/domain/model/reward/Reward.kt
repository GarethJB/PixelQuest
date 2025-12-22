package com.jb.pixelquest.shared.domain.model.reward

/**
 * 보상 도메인 모델
 */
data class Reward(
    val id: String,
    val type: RewardType,
    val name: String,
    val description: String,
    val iconPath: String?,
    val rarity: RewardRarity = RewardRarity.COMMON
)

