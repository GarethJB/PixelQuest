package com.jb.pixelquest.feature.quest.viewmodel

import androidx.lifecycle.ViewModel
import com.jb.pixelquest.feature.quest.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 * Quest ?”ë©´ ViewModel
 * Orbit MVI ?¨í„´ ?¬ìš©
 */
@HiltViewModel
class QuestViewModel @Inject constructor(
    // TODO: UseCase ì£¼ì…
    // private val getDailyQuestsUseCase: GetDailyQuestsUseCase,
    // private val getWeeklyQuestsUseCase: GetWeeklyQuestsUseCase,
    // private val startQuestUseCase: StartQuestUseCase,
    // private val completeQuestUseCase: CompleteQuestUseCase,
) : ContainerHost<QuestUiState, QuestSideEffect>, ViewModel() {

    override val container = container<QuestUiState, QuestSideEffect>(
        QuestUiState()
    ) {
        // ì´ˆê¸° ?°ì´??ë¡œë“œ
        loadInitialData()
    }

    /**
     * ì´ˆê¸° ?°ì´??ë¡œë“œ
     */
    private fun loadInitialData() = intent {
        reduce {
            state.copy(isLoading = true)
        }

        // TODO: UseCaseë¥??µí•œ ?°ì´??ë¡œë“œ
        // val dailyQuests = getDailyQuestsUseCase()
        // val weeklyQuests = getWeeklyQuestsUseCase()
        // val activeQuests = getActiveQuestsUseCase()
        // val completedQuests = getCompletedQuestsUseCase()

        // ?„ì‹œ ?°ì´??(ê°œë°œ??
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

    /**
     * ?¡ì…˜ ì²˜ë¦¬
     */
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
                    // TODO: UseCaseë¥??µí•œ ?˜ìŠ¤???œì‘
                    // startQuestUseCase(action.questId)

                    // Studio ?ë””?°ë¡œ ?´ë™
                    postSideEffect(
                        QuestSideEffect.NavigateToStudio(
                            questId = quest.id,
                            requirements = quest.requirements,
                            theme = quest.theme
                        )
                    )

                    // ?íƒœ ?…ë°?´íŠ¸
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
                    // TODO: UseCaseë¥??µí•œ ?˜ìŠ¤???„ë£Œ
                    // completeQuestUseCase(action.questId, action.artworkId)

                    // ?íƒœ ?…ë°?´íŠ¸
                    reduce {
                        state.copy(
                            activeQuests = state.activeQuests.filter { it.id != action.questId },
                            completedQuests = state.completedQuests + quest.copy(status = QuestStatus.COMPLETED),
                            dailyQuests = updateQuestStatus(state.dailyQuests, action.questId, QuestStatus.COMPLETED),
                            weeklyQuests = updateQuestStatus(state.weeklyQuests, action.questId, QuestStatus.COMPLETED),
                            showQuestDetail = false
                        )
                    }

                    postSideEffect(QuestSideEffect.ShowSnackbar("?˜ìŠ¤?¸ë? ?„ë£Œ?ˆìŠµ?ˆë‹¤!"))
                }
            }

            is QuestAction.RefreshQuests -> {
                reduce {
                    state.copy(isLoading = true)
                }

                // TODO: UseCaseë¥??µí•œ ?ˆë¡œê³ ì¹¨
                // val dailyQuests = getDailyQuestsUseCase()
                // val weeklyQuests = getWeeklyQuestsUseCase()

                reduce {
                    state.copy(
                        isLoading = false,
                        dailyQuests = emptyList(), // TODO: ?¤ì œ ?°ì´?°ë¡œ êµì²´
                        weeklyQuests = emptyList() // TODO: ?¤ì œ ?°ì´?°ë¡œ êµì²´
                    )
                }
            }

            is QuestAction.ClaimReward -> {
                // TODO: UseCaseë¥??µí•œ ë³´ìƒ ?˜ë ¹
                // claimRewardUseCase(action.rewardId)

                postSideEffect(QuestSideEffect.ShowSnackbar("ë³´ìƒ???˜ë ¹?ˆìŠµ?ˆë‹¤!"))
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
                // QuestProgressViewModel?ì„œ ì²˜ë¦¬
            }

            is QuestAction.RefreshProgress -> {
                // QuestProgressViewModel?ì„œ ì²˜ë¦¬
            }
        }
    }

    /**
     * ?˜ìŠ¤??IDë¡??˜ìŠ¤??ì°¾ê¸°
     */
    private fun findQuestById(questId: String): ChallengeQuest? {
        val state = container.stateFlow.value
        return state.dailyQuests.find { it.id == questId }
            ?: state.weeklyQuests.find { it.id == questId }
            ?: state.activeQuests.find { it.id == questId }
            ?: state.completedQuests.find { it.id == questId }
    }

    /**
     * ?˜ìŠ¤???íƒœ ?…ë°?´íŠ¸
     */
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

