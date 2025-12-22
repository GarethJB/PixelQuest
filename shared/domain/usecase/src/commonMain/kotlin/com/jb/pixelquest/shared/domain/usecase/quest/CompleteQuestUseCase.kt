package com.jb.pixelquest.shared.domain.usecase.quest

import com.jb.pixelquest.shared.domain.model.quest.Quest
import com.jb.pixelquest.shared.domain.repository.quest.QuestRepository

/**
 * 퀘스트 완료 UseCase
 */
class CompleteQuestUseCase(
    private val questRepository: QuestRepository
) {
    suspend operator fun invoke(questId: String, artworkId: String): Result<Quest> {
        return questRepository.completeQuest(questId, artworkId)
    }
}

