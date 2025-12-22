package com.jb.pixelquest.shared.domain.usecase.quest

import com.jb.pixelquest.shared.domain.model.quest.QuestStatistics
import com.jb.pixelquest.shared.domain.repository.quest.QuestRepository

/**
 * 퀘스트 통계 조회 UseCase
 */
class GetQuestStatisticsUseCase(
    private val questRepository: QuestRepository
) {
    suspend operator fun invoke(): Result<QuestStatistics> {
        return questRepository.getQuestStatistics()
    }
}

