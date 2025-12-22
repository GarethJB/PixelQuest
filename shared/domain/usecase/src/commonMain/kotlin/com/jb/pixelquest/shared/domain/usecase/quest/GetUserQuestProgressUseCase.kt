package com.jb.pixelquest.shared.domain.usecase.quest

import com.jb.pixelquest.shared.domain.model.quest.UserQuestProgress
import com.jb.pixelquest.shared.domain.repository.quest.QuestRepository

/**
 * 사용자 퀘스트 진행도 조회 UseCase
 */
class GetUserQuestProgressUseCase(
    private val questRepository: QuestRepository
) {
    suspend operator fun invoke(): Result<UserQuestProgress> {
        return questRepository.getUserQuestProgress()
    }
}

