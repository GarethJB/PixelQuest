package com.jb.pixelquest.feature.quest.viewmodel

import androidx.lifecycle.ViewModel
import com.jb.pixelquest.feature.quest.model.Achievement
import com.jb.pixelquest.feature.quest.model.Activity
import com.jb.pixelquest.feature.quest.model.QuestProgressState
import com.jb.pixelquest.feature.quest.model.QuestStatistics
import com.jb.pixelquest.feature.quest.model.Reward
import com.jb.pixelquest.feature.quest.model.UserQuestProgress
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class QuestProgressViewModel @Inject constructor(
    // TODO: UseCase 주입
    // private val getQuestProgressUseCase: GetQuestProgressUseCase,
    // private val getQuestStatisticsUseCase: GetQuestStatisticsUseCase,
    // private val getAchievementsUseCase: GetAchievementsUseCase,
) : ContainerHost<QuestProgressState, Nothing>, ViewModel() {

    override val container = container<QuestProgressState, Nothing>(
        QuestProgressState()
    ) {
        loadInitialData()
    }

    private fun loadInitialData() = intent {
        reduce {
            state.copy(isLoading = true)
        }

        // val progress = getQuestProgressUseCase()
        // val statistics = getQuestStatisticsUseCase()
        // val achievements = getAchievementsUseCase()
        // val activities = getRecentActivitiesUseCase()
        // val rewards = getEarnedRewardsUseCase()

        val mockProgress = UserQuestProgress()
        val mockStatistics = QuestStatistics()
        val mockAchievements = emptyList<Achievement>()
        val mockActivities = emptyList<Activity>()
        val mockRewards = emptyList<Reward>()

        reduce {
            state.copy(
                isLoading = false,
                userProgress = mockProgress,
                statistics = mockStatistics,
                achievements = mockAchievements,
                recentActivity = mockActivities,
                earnedRewards = mockRewards
            )
        }
    }

    fun refreshProgress() = intent {
        reduce {
            state.copy(isLoading = true)
        }

        // val progress = getQuestProgressUseCase()
        // val statistics = getQuestStatisticsUseCase()

        reduce {
            state.copy(
                isLoading = false,
                userProgress = state.userProgress,
                statistics = state.statistics
            )
        }
    }
}

