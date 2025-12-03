package com.jb.pixelquest.feature.quest.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment.Companion.Center
import com.jb.pixelquest.feature.quest.model.QuestAction
import com.jb.pixelquest.feature.quest.model.QuestTab
import com.jb.pixelquest.feature.quest.model.QuestUiState
import com.jb.pixelquest.feature.quest.ui.component.ChallengeQuestList
import com.jb.pixelquest.feature.quest.ui.component.QuestTabRow
import com.jb.pixelquest.feature.quest.ui.screen.QuestDetailScreen
import com.jb.pixelquest.presentation.component.ScreenHeader
import com.jb.pixelquest.presentation.resources.R

/**
 * Quest 메인 화면
 * State Hoisting 패턴: 상태는 상위에서 관리하고, 액션만 전달받음
 */
@Composable
fun QuestScreen(
    uiState: QuestUiState,
    onAction: (QuestAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            ScreenHeader(titleResId = R.string.quest_title)
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // 탭 행
            QuestTabRow(
                selectedTab = uiState.selectedTab,
                onTabSelected = { tab ->
                    onAction(QuestAction.SelectTab(tab))
                }
            )

            // 퀘스트 리스트
            Box(modifier = Modifier.fillMaxSize()) {
                when (uiState.selectedTab) {
                    QuestTab.DAILY -> {
                        ChallengeQuestList(
                            quests = uiState.dailyQuests,
                            onQuestSelected = { quest ->
                                onAction(QuestAction.SelectQuest(quest))
                            }
                        )
                    }
                    QuestTab.WEEKLY -> {
                        ChallengeQuestList(
                            quests = uiState.weeklyQuests,
                            onQuestSelected = { quest ->
                                onAction(QuestAction.SelectQuest(quest))
                            }
                        )
                    }
                    QuestTab.ACTIVE -> {
                        ChallengeQuestList(
                            quests = uiState.activeQuests,
                            onQuestSelected = { quest ->
                                onAction(QuestAction.SelectQuest(quest))
                            }
                        )
                    }
                    QuestTab.COMPLETED -> {
                        ChallengeQuestList(
                            quests = uiState.completedQuests,
                            onQuestSelected = { quest ->
                                onAction(QuestAction.SelectQuest(quest))
                            }
                        )
                    }
                }

                // 로딩 인디케이터
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Center)
                    )
                }
            }
        }
    }

    // 퀘스트 상세 다이얼로그
    if (uiState.showQuestDetail && uiState.selectedQuest != null) {
        QuestDetailScreen(
            quest = uiState.selectedQuest,
            onStartQuest = {
                onAction(QuestAction.StartQuest(uiState.selectedQuest.id))
            },
            onDismiss = {
                onAction(QuestAction.HideQuestDetail)
            }
        )
    }
}

