package com.jb.pixelquest.shared.data.remote.mapper

import com.jb.pixelquest.shared.data.remote.dto.AchievementDto
import com.jb.pixelquest.shared.data.remote.dto.ActivityDto
import com.jb.pixelquest.shared.data.remote.dto.QuestDto
import com.jb.pixelquest.shared.data.remote.dto.QuestRequirementsDto
import com.jb.pixelquest.shared.data.remote.dto.QuestStatisticsDto
import com.jb.pixelquest.shared.data.remote.dto.RewardDto
import com.jb.pixelquest.shared.data.remote.dto.UserQuestProgressDto
import com.jb.pixelquest.shared.domain.model.quest.Achievement
import com.jb.pixelquest.shared.domain.model.quest.Activity
import com.jb.pixelquest.shared.domain.model.quest.ActivityType
import com.jb.pixelquest.shared.domain.model.quest.Quest
import com.jb.pixelquest.shared.domain.model.quest.QuestDifficulty
import com.jb.pixelquest.shared.domain.model.quest.QuestRequirements
import com.jb.pixelquest.shared.domain.model.quest.QuestStatistics
import com.jb.pixelquest.shared.domain.model.quest.QuestStatus
import com.jb.pixelquest.shared.domain.model.quest.QuestType
import com.jb.pixelquest.shared.domain.model.quest.UserQuestProgress
import com.jb.pixelquest.shared.domain.model.common.CanvasSize
import com.jb.pixelquest.shared.domain.model.reward.Reward
import com.jb.pixelquest.shared.domain.model.reward.RewardRarity
import com.jb.pixelquest.shared.domain.model.reward.RewardType

/**
 * Quest 관련 DTO ↔ Domain 모델 Mapper
 */
object QuestMapper {
    
    fun QuestDto.toDomain(): Quest {
        return Quest(
            id = id,
            title = title,
            description = description,
            theme = theme,
            questType = questType.toQuestType(),
            difficulty = difficulty.toQuestDifficulty(),
            rewards = rewards.map { it.toDomain() },
            requirements = requirements.toDomain(),
            status = status.toQuestStatus(),
            deadline = deadline,
            thumbnailPath = thumbnailPath,
            participantCount = participantCount,
            startDate = startDate
        )
    }
    
    fun QuestRequirementsDto.toDomain(): QuestRequirements {
        return QuestRequirements(
            canvasSize = if (canvasWidth != null && canvasHeight != null) {
                CanvasSize(canvasWidth, canvasHeight)
            } else null,
            colorLimit = colorLimit,
            themeKeywords = themeKeywords,
            minPixels = minPixels,
            maxPixels = maxPixels
        )
    }
    
    fun RewardDto.toDomain(): Reward {
        return Reward(
            id = id,
            type = type.toRewardType(),
            name = name,
            description = description,
            iconPath = iconPath,
            rarity = rarity.toRewardRarity()
        )
    }
    
    fun UserQuestProgressDto.toDomain(): UserQuestProgress {
        return UserQuestProgress(
            totalQuestsCompleted = totalQuestsCompleted,
            dailyQuestsCompleted = dailyQuestsCompleted,
            weeklyQuestsCompleted = weeklyQuestsCompleted,
            totalRewardsEarned = totalRewardsEarned,
            currentLevel = currentLevel,
            experiencePoints = experiencePoints,
            nextLevelExp = nextLevelExp,
            streakDays = streakDays
        )
    }
    
    fun QuestStatisticsDto.toDomain(): QuestStatistics {
        return QuestStatistics(
            totalArtworksCreated = totalArtworksCreated,
            totalTimeSpent = totalTimeSpent,
            favoriteTheme = favoriteTheme,
            mostUsedPalette = mostUsedPalette,
            averageCompletionTime = averageCompletionTime,
            bestStreak = bestStreak
        )
    }
    
    fun AchievementDto.toDomain(): Achievement {
        return Achievement(
            id = id,
            name = name,
            description = description,
            iconPath = iconPath,
            progress = progress,
            target = target,
            isUnlocked = isUnlocked,
            unlockedDate = unlockedDate,
            reward = reward?.toDomain()
        )
    }
    
    fun ActivityDto.toDomain(): Activity {
        return Activity(
            id = id,
            type = type.toActivityType(),
            title = title,
            description = description,
            timestamp = timestamp,
            relatedId = relatedId
        )
    }
    
    private fun String.toQuestType(): QuestType {
        return try {
            QuestType.valueOf(this.uppercase())
        } catch (e: IllegalArgumentException) {
            QuestType.DAILY
        }
    }
    
    private fun String.toQuestDifficulty(): QuestDifficulty {
        return try {
            QuestDifficulty.valueOf(this.uppercase())
        } catch (e: IllegalArgumentException) {
            QuestDifficulty.EASY
        }
    }
    
    private fun String.toQuestStatus(): QuestStatus {
        return try {
            QuestStatus.valueOf(this.uppercase())
        } catch (e: IllegalArgumentException) {
            QuestStatus.AVAILABLE
        }
    }
    
    private fun String.toRewardType(): RewardType {
        return try {
            RewardType.valueOf(this.uppercase())
        } catch (e: IllegalArgumentException) {
            RewardType.ITEM
        }
    }
    
    private fun String.toRewardRarity(): RewardRarity {
        return try {
            RewardRarity.valueOf(this.uppercase())
        } catch (e: IllegalArgumentException) {
            RewardRarity.COMMON
        }
    }
    
    private fun String.toActivityType(): ActivityType {
        return try {
            ActivityType.valueOf(this.uppercase())
        } catch (e: IllegalArgumentException) {
            ActivityType.QUEST_COMPLETED
        }
    }
}

