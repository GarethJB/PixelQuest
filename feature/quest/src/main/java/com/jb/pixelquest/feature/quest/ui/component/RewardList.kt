package com.jb.pixelquest.feature.quest.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jb.pixelquest.feature.quest.model.Reward
import com.jb.pixelquest.feature.quest.model.RewardType
import com.jb.pixelquest.shared.presentation.resources.R

@Composable
fun RewardList(
    rewards: List<Reward>,
    onRewardSelected: ((Reward) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        items(
            items = rewards,
            key = { it.id }
        ) { reward ->
            RewardCard(
                reward = reward,
                onClick = { onRewardSelected?.invoke(reward) },
                modifier = Modifier.width(120.dp)
            )
        }
    }
}

@Composable
private fun RewardCard(
    reward: Reward,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(64.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (reward.iconPath != null) {
                        AsyncImage(
                            model = reward.iconPath,
                            contentDescription = reward.name,
                            modifier = Modifier.size(48.dp)
                        )
                    } else {
                        Text(
                            text = reward.name.take(1),
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
            }

            Text(
                text = reward.name,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Medium,
                maxLines = 2
            )

            Text(
                text = getRewardTypeText(reward.type),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun getRewardTypeText(type: RewardType): String {
    return when (type) {
        RewardType.PALETTE -> stringResource(id = R.string.reward_palette)
        RewardType.BRUSH -> stringResource(id = R.string.reward_brush)
        RewardType.BADGE -> stringResource(id = R.string.reward_badge)
        RewardType.ITEM -> stringResource(id = R.string.reward_item)
    }
}

