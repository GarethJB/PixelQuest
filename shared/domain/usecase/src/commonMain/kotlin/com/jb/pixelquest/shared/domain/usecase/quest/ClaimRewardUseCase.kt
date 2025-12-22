package com.jb.pixelquest.shared.domain.usecase.quest

import com.jb.pixelquest.shared.domain.repository.quest.QuestRepository

/**
 * 보상 수령 UseCase
 */
class ClaimRewardUseCase(
    private val questRepository: QuestRepository
) {
    suspend operator fun invoke(rewardId: String): Result<Unit> {
        return questRepository.claimReward(rewardId)
    }
}

