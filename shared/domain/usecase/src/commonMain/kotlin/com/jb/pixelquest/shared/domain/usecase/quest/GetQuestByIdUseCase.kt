package com.jb.pixelquest.shared.domain.usecase.quest

import com.jb.pixelquest.shared.domain.model.quest.Quest
import com.jb.pixelquest.shared.domain.repository.quest.QuestRepository

/**
 * 퀘스트 ID로 조회 UseCase
 */
class GetQuestByIdUseCase(
    private val questRepository: QuestRepository
) {
    suspend operator fun invoke(id: String): Result<Quest> {
        return questRepository.getQuestById(id)
    }
}

