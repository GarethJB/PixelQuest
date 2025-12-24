package com.jb.pixelquest.feature.quest.viewmodel

import androidx.lifecycle.ViewModel
import com.jb.pixelquest.feature.quest.model.ChallengeQuest
import com.jb.pixelquest.feature.quest.model.QuestAction
import com.jb.pixelquest.feature.quest.model.QuestSideEffect
import com.jb.pixelquest.feature.quest.model.QuestStatus
import com.jb.pixelquest.feature.quest.model.QuestUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class QuestViewModel @Inject constructor(
    // TODO: UseCase 주입
    // private val getDailyQuestsUseCase: GetDailyQuestsUseCase,
    // private val getWeeklyQuestsUseCase: GetWeeklyQuestsUseCase,
    // private val startQuestUseCase: StartQuestUseCase,
    // private val completeQuestUseCase: CompleteQuestUseCase,
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

        // val dailyQuests = getDailyQuestsUseCase()
        // val weeklyQuests = getWeeklyQuestsUseCase()
        // val activeQuests = getActiveQuestsUseCase()
        // val completedQuests = getCompletedQuestsUseCase()

        val mockDailyQuests = emptyList<ChallengeQuest>()
        val mockWeeklyQuests = emptyList<ChallengeQuest>()
        val mockActiveQuests = emptyList<ChallengeQuest>()
        val mockCompletedQuests = emptyList<ChallengeQuest>()

        reduce {
            state.copy(
                isLoading = false,
                dailyQuests = mockDailyQuests,
                weeklyQuests = mockWeeklyQuests,
                activeQuests = mockActiveQuests,
                completedQuests = mockCompletedQuests
            )
        }
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
                    // startQuestUseCase(action.questId)

                    postSideEffect(
                        QuestSideEffect.NavigateToStudio(
                            questId = quest.id,
                            requirements = quest.requirements,
                            theme = quest.theme
                        )
                    )

                    reduce {
                        state.copy(
                            activeQuests = state.activeQuests + quest.copy(status = QuestStatus.IN_PROGRESS),
                            dailyQuests = updateQuestStatus(state.dailyQuests, action.questId, QuestStatus.IN_PROGRESS),
                            weeklyQuests = updateQuestStatus(state.weeklyQuests, action.questId, QuestStatus.IN_PROGRESS),
                            showQuestDetail = false
                        )
                    }
                }
            }

            is QuestAction.CompleteQuest -> {
                val quest = findQuestById(action.questId)
                if (quest != null && quest.status == QuestStatus.IN_PROGRESS) {
                    // completeQuestUseCase(action.questId, action.artworkId)

                    reduce {
                        state.copy(
                            activeQuests = state.activeQuests.filter { it.id != action.questId },
                            completedQuests = state.completedQuests + quest.copy(status = QuestStatus.COMPLETED),
                            dailyQuests = updateQuestStatus(state.dailyQuests, action.questId, QuestStatus.COMPLETED),
                            weeklyQuests = updateQuestStatus(state.weeklyQuests, action.questId, QuestStatus.COMPLETED),
                            showQuestDetail = false
                        )
                    }

                    postSideEffect(QuestSideEffect.ShowSnackbar(""))
                }
            }

            is QuestAction.RefreshQuests -> {
                reduce {
                    state.copy(isLoading = true)
                }

                // val dailyQuests = getDailyQuestsUseCase()
                // val weeklyQuests = getWeeklyQuestsUseCase()

                reduce {
                    state.copy(
                        isLoading = false,
                        dailyQuests = emptyList(),
                        weeklyQuests = emptyList()
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

