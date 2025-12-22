package com.jb.pixelquest.shared.domain.usecase.quest

import com.jb.pixelquest.shared.domain.model.quest.Achievement
import com.jb.pixelquest.shared.domain.repository.quest.QuestRepository

/**
 * 업적 목록 조회 UseCase
 */
class GetAchievementsUseCase(
    private val questRepository: QuestRepository
) {
    suspend operator fun invoke(): Result<List<Achievement>> {
        return questRepository.getAchievements()
    }
}

