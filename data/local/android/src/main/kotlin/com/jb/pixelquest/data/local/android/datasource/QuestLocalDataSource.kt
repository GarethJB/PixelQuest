package com.jb.pixelquest.data.local.android.datasource

import com.jb.pixelquest.data.local.android.dao.QuestDao
import com.jb.pixelquest.data.local.android.mapper.QuestMapper.toDomain
import com.jb.pixelquest.data.local.android.mapper.QuestMapper.toEntity
import com.jb.pixelquest.shared.domain.model.quest.Quest
import com.jb.pixelquest.shared.domain.model.quest.QuestStatus
import com.jb.pixelquest.shared.domain.model.quest.QuestType

/**
 * 퀘스트 Local DataSource 구현체
 */
class QuestLocalDataSource(
    private val questDao: QuestDao
) {
    suspend fun getQuestById(id: String): Quest? {
        return questDao.getQuestById(id)?.toDomain()
    }

    suspend fun getQuestsByType(type: QuestType): List<Quest> {
        return questDao.getQuestsByType(type.name)
            .map { it.toDomain() }
    }

    suspend fun getQuestsByStatus(status: QuestStatus): List<Quest> {
        return questDao.getQuestsByStatus(status.name)
            .map { it.toDomain() }
    }

    suspend fun getAllQuests(): List<Quest> {
        return questDao.getAllQuests()
            .map { it.toDomain() }
    }

    suspend fun saveQuest(quest: Quest) {
        questDao.insertQuest(quest.toEntity())
    }

    suspend fun saveQuests(quests: List<Quest>) {
        questDao.insertQuests(quests.map { it.toEntity() })
    }

    suspend fun updateQuest(quest: Quest) {
        questDao.updateQuest(quest.toEntity())
    }

    suspend fun deleteQuest(id: String) {
        questDao.deleteQuest(id)
    }

    suspend fun deleteAllQuests() {
        questDao.deleteAllQuests()
    }

    suspend fun clearOldQuests(olderThanDays: Int = 7) {
        val timestamp = System.currentTimeMillis() - (olderThanDays * 24 * 60 * 60 * 1000L)
        questDao.deleteQuestsOlderThan(timestamp)
    }
}

