package com.jb.pixelquest.feature.mypage.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jb.pixelquest.feature.mypage.model.InventoryItem
import com.jb.pixelquest.feature.mypage.model.ItemRarity
import com.jb.pixelquest.feature.mypage.ui.component.getRarityColor
import com.jb.pixelquest.feature.mypage.ui.component.getRarityText
import com.jb.pixelquest.shared.presentation.resources.R

/**
 * ?∏Î≤§?†Î¶¨ ?ÑÏù¥???ÅÏÑ∏ ?îÎ©¥
 * State Hoisting: ?ÅÌÉú?Ä ?°ÏÖòÎß??ÑÎã¨Î∞õÏùå
 */
@Composable
fun InventoryItemDetailScreen(
    item: InventoryItem,
    onEquipClick: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = item.name,
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
                // ?ÑÏù¥ÏΩ?
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                        modifier = Modifier.size(120.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            if (item.iconUrl != null) {
                                AsyncImage(
                                    model = item.iconUrl,
                                    contentDescription = item.name,
                                    modifier = Modifier.size(100.dp),
                                    contentScale = ContentScale.Fit
                                )
                            } else {
                                Text(
                                    text = item.name.take(1),
                                    style = MaterialTheme.typography.displaySmall
                                )
                            }
                        }
                    }
                }

                // ?§Î™Ö
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium
                )

                // ?¨Í???
                Surface(
                    color = getRarityColor(item.rarity).copy(alpha = 0.2f),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = getRarityText(item.rarity),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = getRarityColor(item.rarity),
                        fontWeight = FontWeight.Bold
                    )
                }

                // ?çÎìù Í≤ΩÎ°ú
                if (item.obtainedFrom != null) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.obtained_from),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = item.obtainedFrom,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onEquipClick) {
                Text(
                    text = if (item.isEquipped) {
                        stringResource(id = R.string.unequip)
                    } else {
                        stringResource(id = R.string.equip)
                    }
                )
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

