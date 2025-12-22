package com.jb.pixelquest.shared.domain.usecase.quest

import com.jb.pixelquest.shared.domain.model.quest.Activity
import com.jb.pixelquest.shared.domain.repository.quest.QuestRepository

/**
 * 최근 활동 목록 조회 UseCase
 */
class GetRecentActivitiesUseCase(
    private val questRepository: QuestRepository
) {
    suspend operator fun invoke(limit: Int = 20): Result<List<Activity>> {
        return questRepository.getRecentActivities(limit)
    }
}

