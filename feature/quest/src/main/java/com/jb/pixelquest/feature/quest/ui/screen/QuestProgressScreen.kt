package com.jb.pixelquest.feature.quest.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.feature.quest.model.Achievement
import com.jb.pixelquest.feature.quest.model.Activity
import com.jb.pixelquest.feature.quest.model.QuestProgressState
import com.jb.pixelquest.feature.quest.model.QuestStatistics
import com.jb.pixelquest.feature.quest.model.UserQuestProgress
import com.jb.pixelquest.feature.quest.ui.component.RewardList
import com.jb.pixelquest.presentation.component.ScreenHeader
import com.jb.pixelquest.shared.presentation.resources.R

@Composable
fun QuestProgressScreen(
    progressState: QuestProgressState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            ScreenHeader(titleResId = R.string.quest_progress_title)
        }
    ) { paddingValues ->
        if (progressState.isLoading) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                item {
                    ProgressOverviewCard(progress = progressState.userProgress)
                }

                item {
                    StatisticsCard(statistics = progressState.statistics)
                }

                if (progressState.achievements.isNotEmpty()) {
                    item {
                        Text(
                            text = stringResource(id = R.string.achievements),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    items(
                        items = progressState.achievements,
                        key = { it.id }
                    ) { achievement ->
                        AchievementCard(achievement = achievement)
                    }
                } else {
                    item {
                        EmptyAchievementsCard()
                    }
                }

                if (progressState.recentActivity.isNotEmpty()) {
                    item {
                        Text(
                            text = stringResource(id = R.string.recent_activity),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    items(
                        items = progressState.recentActivity,
                        key = { it.id }
                    ) { activity ->
                        ActivityCard(activity = activity)
                    }
                } else {
                    item {
                        EmptyActivityCard()
                    }
                }

                if (progressState.earnedRewards.isNotEmpty()) {
                    item {
                        Text(
                            text = stringResource(id = R.string.earned_rewards),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    item {
                        RewardList(rewards = progressState.earnedRewards)
                    }
                } else {
                    item {
                        EmptyRewardsCard()
                    }
                }
            }
        }
    }
}

@Composable
private fun ProgressOverviewCard(
    progress: UserQuestProgress,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.quest_progress_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${stringResource(id = R.string.current_level)} ${progress.currentLevel}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "${progress.experiencePoints}/${progress.nextLevelExp} ${stringResource(id = R.string.experience_points)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                LinearProgressIndicator(
                    progress = { progress.experiencePoints.toFloat() / progress.nextLevelExp.toFloat() },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatItem(
                    label = stringResource(id = R.string.total_quests_completed),
                    value = progress.totalQuestsCompleted.toString(),
                    modifier = Modifier.weight(1f)
                )
                StatItem(
                    label = stringResource(id = R.string.daily_quests_completed),
                    value = progress.dailyQuestsCompleted.toString(),
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatItem(
                    label = stringResource(id = R.string.weekly_quests_completed),
                    value = progress.weeklyQuestsCompleted.toString(),
                    modifier = Modifier.weight(1f)
                )
                StatItem(
                    label = stringResource(id = R.string.total_rewards_earned),
                    value = progress.totalRewardsEarned.toString(),
                    modifier = Modifier.weight(1f)
                )
            }
            StatItem(
                label = stringResource(id = R.string.streak_days),
                value = "${progress.streakDays}??",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun StatItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun StatisticsCard(
    statistics: QuestStatistics,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.statistics),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            if (statistics.totalArtworksCreated > 0) {
                StatRow(
                    label = stringResource(id = R.string.total_artworks),
                    value = statistics.totalArtworksCreated.toString()
                )
            }

            if (statistics.totalTimeSpent > 0) {
                StatRow(
                    label = stringResource(id = R.string.total_time_spent),
                    value = "${statistics.totalTimeSpent}�?"
                )
            }

            if (statistics.favoriteTheme != null) {
                StatRow(
                    label = stringResource(id = R.string.favorite_theme),
                    value = statistics.favoriteTheme
                )
            }

            if (statistics.mostUsedPalette != null) {
                StatRow(
                    label = stringResource(id = R.string.most_used_palette),
                    value = statistics.mostUsedPalette
                )
            }
        }
    }
}

@Composable
private fun StatRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun AchievementCard(
    achievement: Achievement,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (achievement.isUnlocked) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(48.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (achievement.iconPath.isNotEmpty()) {
                        Text(
                            text = achievement.name.take(1),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = achievement.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = achievement.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                LinearProgressIndicator(
                    progress = { achievement.progress.toFloat() / achievement.target.toFloat() },
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "${achievement.progress}/${achievement.target}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun ActivityCard(
    activity: Activity,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = activity.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = activity.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = formatTimestamp(activity.timestamp),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun EmptyAchievementsCard() {
    EmptyStateCard(message = stringResource(id = R.string.no_achievements))
}

@Composable
private fun EmptyActivityCard() {
    EmptyStateCard(message = stringResource(id = R.string.no_activity))
}

@Composable
private fun EmptyRewardsCard() {
    EmptyStateCard(message = stringResource(id = R.string.no_rewards))
}

@Composable
private fun EmptyStateCard(
    message: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Text(
            text = message,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

private fun formatTimestamp(timestamp: Long): String {
    return ""
}

