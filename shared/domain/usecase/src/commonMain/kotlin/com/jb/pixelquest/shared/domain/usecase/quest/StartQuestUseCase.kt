package com.jb.pixelquest.shared.domain.usecase.quest

import com.jb.pixelquest.shared.domain.model.quest.Quest
import com.jb.pixelquest.shared.domain.repository.quest.QuestRepository

/**
 * 퀘스트 시작 UseCase
 */
class StartQuestUseCase(
    private val questRepository: QuestRepository
) {
    suspend operator fun invoke(questId: String): Result<Quest> {
        return questRepository.startQuest(questId)
    }
}

