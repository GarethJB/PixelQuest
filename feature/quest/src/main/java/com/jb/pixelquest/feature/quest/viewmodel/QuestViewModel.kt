package com.jb.pixelquest.feature.quest.viewmodel

import androidx.lifecycle.ViewModel
import com.jb.pixelquest.feature.quest.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 * Quest 화면 ViewModel
 * Orbit MVI 패턴 사용
 */
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
        // 초기 데이터 로드
        loadInitialData()
    }

    /**
     * 초기 데이터 로드
     */
    private fun loadInitialData() = intent {
        reduce {
            state.copy(isLoading = true)
        }

        // TODO: UseCase를 통한 데이터 로드
        // val dailyQuests = getDailyQuestsUseCase()
        // val weeklyQuests = getWeeklyQuestsUseCase()
        // val activeQuests = getActiveQuestsUseCase()
        // val completedQuests = getCompletedQuestsUseCase()

        // 임시 데이터 (개발용)
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
     * 액션 처리
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
                    // TODO: UseCase를 통한 퀘스트 시작
                    // startQuestUseCase(action.questId)

                    // Studio 에디터로 이동
                    postSideEffect(
                        QuestSideEffect.NavigateToStudio(
                            questId = quest.id,
                            requirements = quest.requirements,
                            theme = quest.theme
                        )
                    )

                    // 상태 업데이트
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
                    // TODO: UseCase를 통한 퀘스트 완료
                    // completeQuestUseCase(action.questId, action.artworkId)

                    // 상태 업데이트
                    reduce {
                        state.copy(
                            activeQuests = state.activeQuests.filter { it.id != action.questId },
                            completedQuests = state.completedQuests + quest.copy(status = QuestStatus.COMPLETED),
                            dailyQuests = updateQuestStatus(state.dailyQuests, action.questId, QuestStatus.COMPLETED),
                            weeklyQuests = updateQuestStatus(state.weeklyQuests, action.questId, QuestStatus.COMPLETED),
                            showQuestDetail = false
                        )
                    }

                    postSideEffect(QuestSideEffect.ShowSnackbar("퀘스트를 완료했습니다!"))
                }
            }

            is QuestAction.RefreshQuests -> {
                reduce {
                    state.copy(isLoading = true)
                }

                // TODO: UseCase를 통한 새로고침
                // val dailyQuests = getDailyQuestsUseCase()
                // val weeklyQuests = getWeeklyQuestsUseCase()

                reduce {
                    state.copy(
                        isLoading = false,
                        dailyQuests = emptyList(), // TODO: 실제 데이터로 교체
                        weeklyQuests = emptyList() // TODO: 실제 데이터로 교체
                    )
                }
            }

            is QuestAction.ClaimReward -> {
                // TODO: UseCase를 통한 보상 수령
                // claimRewardUseCase(action.rewardId)

                postSideEffect(QuestSideEffect.ShowSnackbar("보상을 수령했습니다!"))
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
                // QuestProgressViewModel에서 처리
            }

            is QuestAction.RefreshProgress -> {
                // QuestProgressViewModel에서 처리
            }
        }
    }

    /**
     * 퀘스트 ID로 퀘스트 찾기
     */
    private fun findQuestById(questId: String): ChallengeQuest? {
        val state = container.stateFlow.value
        return state.dailyQuests.find { it.id == questId }
            ?: state.weeklyQuests.find { it.id == questId }
            ?: state.activeQuests.find { it.id == questId }
            ?: state.completedQuests.find { it.id == questId }
    }

    /**
     * 퀘스트 상태 업데이트
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

