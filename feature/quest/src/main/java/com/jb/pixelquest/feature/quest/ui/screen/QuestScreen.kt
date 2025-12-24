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
import com.jb.pixelquest.shared.presentation.resources.R

/**
 * Quest Î©îÏù∏ ?îÎ©¥
 * State Hoisting ?®ÌÑ¥: ?ÅÌÉú???ÅÏúÑ?êÏÑú Í¥ÄÎ¶¨ÌïòÍ≥? ?°ÏÖòÎß??ÑÎã¨Î∞õÏùå
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
            // ????
            QuestTabRow(
                selectedTab = uiState.selectedTab,
                onTabSelected = { tab ->
                    onAction(QuestAction.SelectTab(tab))
                }
            )

            // ?òÏä§??Î¶¨Ïä§??
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

                // Î°úÎî© ?∏ÎîîÏºÄ?¥ÌÑ∞
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Center)
                    )
                }
            }
        }
    }

    // ?òÏä§???ÅÏÑ∏ ?§Ïù¥?ºÎ°úÍ∑?
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

