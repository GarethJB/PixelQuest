package com.jb.pixelquest.feature.quest.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.feature.quest.model.QuestTab
import com.jb.pixelquest.presentation.resources.R

/**
 * Quest 탭 행
 * State Hoisting: 선택 이벤트만 상위로 전달
 */
@Composable
fun QuestTabRow(
    selectedTab: QuestTab,
    onTabSelected: (QuestTab) -> Unit,
    modifier: Modifier = Modifier
) {
    ScrollableTabRow(
        selectedTabIndex = QuestTab.values().indexOf(selectedTab),
        modifier = modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        edgePadding = 16.dp
    ) {
        QuestTab.values().forEach { tab ->
            Tab(
                selected = selectedTab == tab,
                onClick = { onTabSelected(tab) },
                text = {
                    Text(
                        text = when (tab) {
                            QuestTab.DAILY -> stringResource(id = R.string.daily_quests)
                            QuestTab.WEEKLY -> stringResource(id = R.string.weekly_quests)
                            QuestTab.ACTIVE -> stringResource(id = R.string.active_quests)
                            QuestTab.COMPLETED -> stringResource(id = R.string.completed_quests)
                        }
                    )
                }
            )
        }
    }
}

