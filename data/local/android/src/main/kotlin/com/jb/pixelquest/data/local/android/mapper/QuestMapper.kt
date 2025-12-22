package com.jb.pixelquest.data.local.android.mapper

import com.google.gson.Gson
import com.jb.pixelquest.data.local.android.entity.QuestEntity
import com.jb.pixelquest.shared.domain.model.quest.Quest
import com.jb.pixelquest.shared.domain.model.quest.QuestRequirements
import com.jb.pixelquest.shared.domain.model.quest.QuestStatus
import com.jb.pixelquest.shared.domain.model.quest.QuestType
import com.jb.pixelquest.shared.domain.model.reward.Reward

/**
 * Quest Entity ↔ Domain 모델 Mapper
 */
object QuestMapper {
    private val gson = Gson()

    fun QuestEntity.toDomain(): Quest {
        val rewards = gson.fromJson<List<Reward>>(
            rewardsJson,
            object : com.google.gson.reflect.TypeToken<List<Reward>>() {}.type
        ) ?: emptyList()

        val requirements = gson.fromJson<QuestRequirements>(
            requirementsJson,
            QuestRequirements::class.java
        ) ?: QuestRequirements()

        return Quest(
            id = id,
            title = title,
            description = description,
            theme = theme,
            questType = questType.toQuestType(),
            difficulty = difficulty.toQuestDifficulty(),
            rewards = rewards,
            requirements = requirements,
            status = status.toQuestStatus(),
            deadline = deadline,
            thumbnailPath = thumbnailPath,
            participantCount = participantCount,
            startDate = startDate
        )
    }

    fun Quest.toEntity(): QuestEntity {
        return QuestEntity(
            id = id,
            title = title,
            description = description,
            theme = theme,
            questType = questType.name,
            difficulty = difficulty.name,
            rewardsJson = gson.toJson(rewards),
            requirementsJson = gson.toJson(requirements),
            status = status.name,
            deadline = deadline,
            thumbnailPath = thumbnailPath,
            participantCount = participantCount,
            startDate = startDate
        )
    }

    private fun String.toQuestType(): QuestType {
        return try {
            QuestType.valueOf(this)
        } catch (e: IllegalArgumentException) {
            QuestType.DAILY
        }
    }

    private fun String.toQuestDifficulty(): com.jb.pixelquest.shared.domain.model.quest.QuestDifficulty {
        return try {
            com.jb.pixelquest.shared.domain.model.quest.QuestDifficulty.valueOf(this)
        } catch (e: IllegalArgumentException) {
            com.jb.pixelquest.shared.domain.model.quest.QuestDifficulty.EASY
        }
    }

    private fun String.toQuestStatus(): QuestStatus {
        return try {
            QuestStatus.valueOf(this)
        } catch (e: IllegalArgumentException) {
            QuestStatus.AVAILABLE
        }
    }
}

