package com.jb.pixelquest.feature.quest.viewmodel

import androidx.lifecycle.ViewModel
import com.jb.pixelquest.feature.quest.model.*
import com.jb.pixelquest.shared.domain.usecase.quest.CompleteQuestUseCase
import com.jb.pixelquest.shared.domain.usecase.quest.GetActiveQuestsUseCase
import com.jb.pixelquest.shared.domain.usecase.quest.GetCompletedQuestsUseCase
import com.jb.pixelquest.shared.domain.usecase.quest.GetDailyQuestsUseCase
import com.jb.pixelquest.shared.domain.usecase.quest.GetWeeklyQuestsUseCase
import com.jb.pixelquest.shared.domain.usecase.quest.StartQuestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class QuestViewModel @Inject constructor(
    private val getDailyQuestsUseCase: GetDailyQuestsUseCase,
    private val getWeeklyQuestsUseCase: GetWeeklyQuestsUseCase,
    private val getActiveQuestsUseCase: GetActiveQuestsUseCase,
    private val getCompletedQuestsUseCase: GetCompletedQuestsUseCase,
    private val startQuestUseCase: StartQuestUseCase,
    private val completeQuestUseCase: CompleteQuestUseCase,
) : ContainerHost<QuestUiState, QuestSideEffect>, ViewModel() {

    override val container = container<QuestUiState, QuestSideEffect>(
        QuestUiState()
    ) {
        loadInitialData()
    }

    private fun loadInitialData() = intent {
        reduce {
            state.copy(isLoading = true)
        }

        val dailyQuestsResult = getDailyQuestsUseCase()
        val weeklyQuestsResult = getWeeklyQuestsUseCase()
        val activeQuestsResult = getActiveQuestsUseCase()
        val completedQuestsResult = getCompletedQuestsUseCase()

        val dailyQuests = dailyQuestsResult.getOrElse { emptyList() }.map { it.toChallengeQuest() }
        val weeklyQuests = weeklyQuestsResult.getOrElse { emptyList() }.map { it.toChallengeQuest() }
        val activeQuests = activeQuestsResult.getOrElse { emptyList() }.map { it.toChallengeQuest() }
        val completedQuests = completedQuestsResult.getOrElse { emptyList() }.map { it.toChallengeQuest() }

        reduce {
            state.copy(
                isLoading = false,
                dailyQuests = dailyQuests,
                weeklyQuests = weeklyQuests,
                activeQuests = activeQuests,
                completedQuests = completedQuests,
                error = dailyQuestsResult.exceptionOrNull()?.message
                    ?: weeklyQuestsResult.exceptionOrNull()?.message
                    ?: activeQuestsResult.exceptionOrNull()?.message
                    ?: completedQuestsResult.exceptionOrNull()?.message
            )
        }
    }
    
    private fun com.jb.pixelquest.shared.domain.model.quest.Quest.toChallengeQuest(): ChallengeQuest {
        return ChallengeQuest(
            id = this.id,
            title = this.title,
            description = this.description,
            theme = this.theme,
            questType = when (this.questType) {
                com.jb.pixelquest.shared.domain.model.quest.QuestType.DAILY -> QuestType.DAILY
                com.jb.pixelquest.shared.domain.model.quest.QuestType.WEEKLY -> QuestType.WEEKLY
            },
            difficulty = when (this.difficulty) {
                com.jb.pixelquest.shared.domain.model.quest.QuestDifficulty.EASY -> QuestDifficulty.EASY
                com.jb.pixelquest.shared.domain.model.quest.QuestDifficulty.MEDIUM -> QuestDifficulty.MEDIUM
                com.jb.pixelquest.shared.domain.model.quest.QuestDifficulty.HARD -> QuestDifficulty.HARD
            },
            rewards = this.rewards.map { reward ->
                Reward(
                    id = reward.id,
                    type = when (reward.type) {
                        com.jb.pixelquest.shared.domain.model.reward.RewardType.PALETTE -> RewardType.PALETTE
                        com.jb.pixelquest.shared.domain.model.reward.RewardType.BRUSH -> RewardType.BRUSH
                        com.jb.pixelquest.shared.domain.model.reward.RewardType.BADGE -> RewardType.BADGE
                        com.jb.pixelquest.shared.domain.model.reward.RewardType.ITEM -> RewardType.ITEM
                    },
                    name = reward.name,
                    description = reward.description,
                    iconPath = reward.iconPath,
                    rarity = when (reward.rarity) {
                        com.jb.pixelquest.shared.domain.model.reward.RewardRarity.COMMON -> RewardRarity.COMMON
                        com.jb.pixelquest.shared.domain.model.reward.RewardRarity.RARE -> RewardRarity.RARE
                        com.jb.pixelquest.shared.domain.model.reward.RewardRarity.EPIC -> RewardRarity.EPIC
                        com.jb.pixelquest.shared.domain.model.reward.RewardRarity.LEGENDARY -> RewardRarity.LEGENDARY
                    }
                )
            },
            requirements = QuestRequirements(
                canvasSize = this.requirements.canvasSize?.let { 
                    androidx.compose.ui.unit.IntSize(it.width, it.height) 
                },
                colorLimit = this.requirements.colorLimit,
                themeKeywords = this.requirements.themeKeywords,
                minPixels = this.requirements.minPixels,
                maxPixels = this.requirements.maxPixels
            ),
            status = when (this.status) {
                com.jb.pixelquest.shared.domain.model.quest.QuestStatus.AVAILABLE -> QuestStatus.AVAILABLE
                com.jb.pixelquest.shared.domain.model.quest.QuestStatus.IN_PROGRESS -> QuestStatus.IN_PROGRESS
                com.jb.pixelquest.shared.domain.model.quest.QuestStatus.COMPLETED -> QuestStatus.COMPLETED
                com.jb.pixelquest.shared.domain.model.quest.QuestStatus.LOCKED -> QuestStatus.LOCKED
            },
            deadline = this.deadline,
            thumbnailPath = this.thumbnailPath,
            participantCount = this.participantCount,
            startDate = this.startDate
        )
    }

    fun handleAction(action: QuestAction) = intent {
        when (action) {
            is QuestAction.SelectTab -> {
                reduce {
                    state.copy(selectedTab = action.tab)
                }
            }

            is QuestAction.SelectQuest -> {
                reduce {
                    state.copy(
                        selectedQuest = action.quest,
                        showQuestDetail = true
                    )
                }
            }

            is QuestAction.HideQuestDetail -> {
                reduce {
                    state.copy(
                        showQuestDetail = false,
                        selectedQuest = null
                    )
                }
            }

            is QuestAction.StartQuest -> {
                val quest = findQuestById(action.questId)
                if (quest != null && quest.status == QuestStatus.AVAILABLE) {
                    reduce {
                        state.copy(isLoading = true)
                    }
                    
                    val result = startQuestUseCase(action.questId)
                    result.onSuccess { startedQuest ->
                        val updatedQuest = startedQuest.toChallengeQuest()
                        postSideEffect(
                            QuestSideEffect.NavigateToStudio(
                                questId = updatedQuest.id,
                                requirements = updatedQuest.requirements,
                                theme = updatedQuest.theme
                            )
                        )

                        reduce {
                            state.copy(
                                isLoading = false,
                                activeQuests = state.activeQuests + updatedQuest,
                                dailyQuests = updateQuestStatus(state.dailyQuests, action.questId, QuestStatus.IN_PROGRESS),
                                weeklyQuests = updateQuestStatus(state.weeklyQuests, action.questId, QuestStatus.IN_PROGRESS),
                                showQuestDetail = false
                            )
                        }
                    }.onFailure { exception ->
                        reduce {
                            state.copy(
                                isLoading = false,
                                error = exception.message
                            )
                        }
                        postSideEffect(QuestSideEffect.ShowSnackbar(exception.message ?: "퀘스트 시작에 실패했습니다."))
                    }
                }
            }

            is QuestAction.CompleteQuest -> {
                val quest = findQuestById(action.questId)
                if (quest != null && quest.status == QuestStatus.IN_PROGRESS) {
                    reduce {
                        state.copy(isLoading = true)
                    }
                    
                    val result = completeQuestUseCase(action.questId, action.artworkId)
                    result.onSuccess { completedQuest ->
                        val updatedQuest = completedQuest.toChallengeQuest()
                        reduce {
                            state.copy(
                                isLoading = false,
                                activeQuests = state.activeQuests.filter { it.id != action.questId },
                                completedQuests = state.completedQuests + updatedQuest,
                                dailyQuests = updateQuestStatus(state.dailyQuests, action.questId, QuestStatus.COMPLETED),
                                weeklyQuests = updateQuestStatus(state.weeklyQuests, action.questId, QuestStatus.COMPLETED),
                                showQuestDetail = false
                            )
                        }

                        postSideEffect(QuestSideEffect.ShowSnackbar("퀘스트를 완료했습니다!"))
                    }.onFailure { exception ->
                        reduce {
                            state.copy(
                                isLoading = false,
                                error = exception.message
                            )
                        }
                        postSideEffect(QuestSideEffect.ShowSnackbar(exception.message ?: "퀘스트 완료에 실패했습니다."))
                    }
                }
            }

            is QuestAction.RefreshQuests -> {
                reduce {
                    state.copy(isLoading = true)
                }

                val dailyQuestsResult = getDailyQuestsUseCase()
                val weeklyQuestsResult = getWeeklyQuestsUseCase()

                val dailyQuests = dailyQuestsResult.getOrElse { emptyList() }.map { it.toChallengeQuest() }
                val weeklyQuests = weeklyQuestsResult.getOrElse { emptyList() }.map { it.toChallengeQuest() }

                reduce {
                    state.copy(
                        isLoading = false,
                        dailyQuests = dailyQuests,
                        weeklyQuests = weeklyQuests,
                        error = dailyQuestsResult.exceptionOrNull()?.message
                            ?: weeklyQuestsResult.exceptionOrNull()?.message
                    )
                }
            }

            is QuestAction.ClaimReward -> {
                // claimRewardUseCase(action.rewardId)

                postSideEffect(QuestSideEffect.ShowSnackbar(""))
            }

            is QuestAction.ShowError -> {
                reduce {
                    state.copy(error = action.message)
                }
                postSideEffect(QuestSideEffect.ShowSnackbar(action.message))
            }

            is QuestAction.ClearError -> {
                reduce {
                    state.copy(error = null)
                }
            }

            is QuestAction.LoadProgress -> {

            }

            is QuestAction.RefreshProgress -> {

            }
        }
    }

    private fun findQuestById(questId: String): ChallengeQuest? {
        val state = container.stateFlow.value
        return state.dailyQuests.find { it.id == questId }
            ?: state.weeklyQuests.find { it.id == questId }
            ?: state.activeQuests.find { it.id == questId }
            ?: state.completedQuests.find { it.id == questId }
    }

    private fun updateQuestStatus(
        quests: List<ChallengeQuest>,
        questId: String,
        newStatus: QuestStatus
    ): List<ChallengeQuest> {
        return quests.map { quest ->
            if (quest.id == questId) {
                quest.copy(status = newStatus)
            } else {
                quest
            }
        }
    }
}

