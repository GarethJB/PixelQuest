package com.jb.pixelquest.feature.quest.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jb.pixelquest.feature.quest.model.ChallengeQuest
import com.jb.pixelquest.feature.quest.model.QuestDifficulty
import com.jb.pixelquest.feature.quest.model.QuestStatus
import com.jb.pixelquest.shared.presentation.resources.R

@Composable
fun ChallengeQuestCard(
    quest: ChallengeQuest,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (quest.thumbnailPath != null) {
                    AsyncImage(
                        model = quest.thumbnailPath,
                        contentDescription = quest.title,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = quest.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = quest.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2
                    )
                }

                QuestStatusBadge(status = quest.status)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = quest.theme,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                DifficultyBadge(difficulty = quest.difficulty)

                Spacer(modifier = Modifier.weight(1f))

                if (quest.participantCount > 0) {
                    Text(
                        text = "${quest.participantCount}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // 보상 미리보기
            if (quest.rewards.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.quest_rewards) + ":",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium
                    )
                    quest.rewards.take(3).forEach { reward ->
                        RewardIcon(reward = reward)
                    }
                    if (quest.rewards.size > 3) {
                        Text(
                            text = "+${quest.rewards.size - 3}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun QuestStatusBadge(
    status: QuestStatus,
    modifier: Modifier = Modifier
) {
    val (text, color) = when (status) {
        QuestStatus.AVAILABLE -> stringResource(id = R.string.quest_available) to Color(0xFF4CAF50)
        QuestStatus.IN_PROGRESS -> stringResource(id = R.string.quest_in_progress) to Color(0xFF2196F3)
        QuestStatus.COMPLETED -> stringResource(id = R.string.quest_completed) to Color(0xFF9E9E9E)
        QuestStatus.LOCKED -> stringResource(id = R.string.quest_locked) to Color(0xFF757575)
    }

    Surface(
        color = color.copy(alpha = 0.2f),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall,
            color = color
        )
    }
}

@Composable
internal fun DifficultyBadge(
    difficulty: QuestDifficulty,
    modifier: Modifier = Modifier
) {
    val (text, color) = when (difficulty) {
        QuestDifficulty.EASY -> stringResource(id = R.string.difficulty_easy) to Color(0xFF4CAF50)
        QuestDifficulty.MEDIUM -> stringResource(id = R.string.difficulty_medium) to Color(0xFFFF9800)
        QuestDifficulty.HARD -> stringResource(id = R.string.difficulty_hard) to Color(0xFFF44336)
    }

    Surface(
        color = color.copy(alpha = 0.2f),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall,
            color = color
        )
    }
}

@Composable
private fun RewardIcon(
    reward: com.jb.pixelquest.feature.quest.model.Reward,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(4.dp),
        modifier = modifier.size(24.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (reward.iconPath != null) {
                AsyncImage(
                    model = reward.iconPath,
                    contentDescription = reward.name,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text(
                    text = reward.name.take(1),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

