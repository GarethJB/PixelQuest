package com.jb.pixelquest.feature.quest.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.feature.quest.model.ChallengeQuest
import com.jb.pixelquest.shared.presentation.resources.R

/**
 * Ï±åÎ¶∞ÏßÄ ?òÏä§??Î¶¨Ïä§??
 * State Hoisting: ?†ÌÉù ?¥Î≤§?∏Îßå ?ÅÏúÑÎ°??ÑÎã¨
 */
@Composable
fun ChallengeQuestList(
    quests: List<ChallengeQuest>,
    onQuestSelected: (ChallengeQuest) -> Unit,
    modifier: Modifier = Modifier
) {
    if (quests.isEmpty()) {
        EmptyQuestList()
    } else {
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(
                items = quests,
                key = { it.id }
            ) { quest ->
                ChallengeQuestCard(
                    quest = quest,
                    onClick = { onQuestSelected(quest) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun EmptyQuestList() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Text(
            text = stringResource(id = R.string.no_quests_available),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

