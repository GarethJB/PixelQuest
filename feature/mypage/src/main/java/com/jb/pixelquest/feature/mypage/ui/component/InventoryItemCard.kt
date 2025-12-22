package com.jb.pixelquest.feature.mypage.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jb.pixelquest.feature.mypage.model.InventoryItem
import com.jb.pixelquest.feature.mypage.model.ItemRarity
import com.jb.pixelquest.shared.presentation.resources.R

@Composable
fun InventoryItemCard(
    item: InventoryItem,
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
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(64.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        if (item.iconUrl != null) {
                            AsyncImage(
                                model = item.iconUrl,
                                contentDescription = item.name,
                                modifier = Modifier.size(48.dp),
                                contentScale = ContentScale.Fit
                            )
                        } else {
                            Text(
                                text = item.name.take(1),
                                style = MaterialTheme.typography.headlineSmall
                            )
                        }
                    }
                }

                if (item.isEquipped) {
                    Surface(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(20.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "??",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }

                if (item.isNew) {
                    Surface(
                        color = MaterialTheme.colorScheme.error,
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .size(20.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "N",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onError
                            )
                        }
                    }
                }
            }

            Text(
                text = item.name,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Surface(
                color = getRarityColor(item.rarity).copy(alpha = 0.2f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = getRarityText(item.rarity),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = getRarityColor(item.rarity)
                )
            }
        }
    }
}

@Composable
internal fun getRarityColor(rarity: ItemRarity): androidx.compose.ui.graphics.Color {
    return when (rarity) {
        ItemRarity.COMMON -> androidx.compose.ui.graphics.Color(0xFF9E9E9E)
        ItemRarity.RARE -> androidx.compose.ui.graphics.Color(0xFF2196F3)
        ItemRarity.EPIC -> androidx.compose.ui.graphics.Color(0xFF9C27B0)
        ItemRarity.LEGENDARY -> androidx.compose.ui.graphics.Color(0xFFFF9800)
    }
}

@Composable
internal fun getRarityText(rarity: ItemRarity): String {
    return when (rarity) {
        ItemRarity.COMMON -> ""
        ItemRarity.RARE -> ""
        ItemRarity.EPIC -> ""
        ItemRarity.LEGENDARY -> ""
    }
}

