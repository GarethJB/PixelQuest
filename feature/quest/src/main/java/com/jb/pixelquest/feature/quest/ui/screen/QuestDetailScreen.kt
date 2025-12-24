package com.jb.pixelquest.feature.quest.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jb.pixelquest.feature.quest.model.ChallengeQuest
import com.jb.pixelquest.feature.quest.model.QuestStatus
import com.jb.pixelquest.feature.quest.ui.component.DifficultyBadge
import com.jb.pixelquest.feature.quest.ui.component.QuestStatusBadge
import com.jb.pixelquest.feature.quest.ui.component.RewardList
import com.jb.pixelquest.shared.presentation.resources.R

@Composable
fun QuestDetailScreen(
    quest: ChallengeQuest,
    onStartQuest: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = quest.title,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // ?�네??
                if (quest.thumbnailPath != null) {
                    AsyncImage(
                        model = quest.thumbnailPath,
                        contentDescription = quest.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }

                Text(
                    text = quest.description,
                    style = MaterialTheme.typography.bodyLarge
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    QuestStatusBadge(status = quest.status)
                    DifficultyBadge(difficulty = quest.difficulty)
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.quest_theme),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = quest.theme,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.quest_requirements),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                    
                    if (quest.requirements.canvasSize != null) {
                        Text(
                            text = ": ${quest.requirements.canvasSize.width}x${quest.requirements.canvasSize.height}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    
                    if (quest.requirements.colorLimit != null) {
                        Text(
                            text = ": ${quest.requirements.colorLimit}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    
                    if (quest.requirements.themeKeywords.isNotEmpty()) {
                        Text(
                            text = " ${quest.requirements.themeKeywords.joinToString(", ")}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                // 보상
                if (quest.rewards.isNotEmpty()) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.quest_rewards),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                        RewardList(rewards = quest.rewards)
                    }
                }

                // 참여????
                if (quest.participantCount > 0) {
                    Text(
                        text = "${stringResource(id = R.string.quest_participants)}: ${quest.participantCount}�?",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        confirmButton = {
            if (quest.status == QuestStatus.AVAILABLE || quest.status == QuestStatus.IN_PROGRESS) {
                Button(onClick = onStartQuest) {
                    Text(
                        text = if (quest.status == QuestStatus.IN_PROGRESS) {
                            stringResource(id = R.string.quest_in_progress)
                        } else {
                            stringResource(id = R.string.start_quest)
                        }
                    )
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(id = R.string.cancel))
            }
        },
        modifier = modifier
    )
}

