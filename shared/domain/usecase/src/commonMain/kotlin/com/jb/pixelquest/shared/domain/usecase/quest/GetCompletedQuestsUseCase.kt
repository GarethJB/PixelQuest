package com.jb.pixelquest.shared.domain.usecase.quest

import com.jb.pixelquest.shared.domain.model.quest.Quest
import com.jb.pixelquest.shared.domain.repository.quest.QuestRepository

/**
 * 완료된 퀘스트 목록 조회 UseCase
 */
class GetCompletedQuestsUseCase(
    private val questRepository: QuestRepository
) {
    suspend operator fun invoke(): Result<List<Quest>> {
        return questRepository.getCompletedQuests()
    }
}

