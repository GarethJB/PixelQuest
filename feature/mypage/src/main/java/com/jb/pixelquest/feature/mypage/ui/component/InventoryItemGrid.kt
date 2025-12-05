package com.jb.pixelquest.feature.mypage.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.feature.mypage.model.InventoryItem
import com.jb.pixelquest.shared.presentation.resources.R

/**
 * ?∏Î≤§?†Î¶¨ ?ÑÏù¥??Í∑∏Î¶¨??
 * State Hoisting: ?†ÌÉù ?¥Î≤§?∏Îßå ?ÅÏúÑÎ°??ÑÎã¨
 */
@Composable
fun InventoryItemGrid(
    items: List<InventoryItem>,
    onItemSelected: (InventoryItem) -> Unit,
    modifier: Modifier = Modifier
) {
    if (items.isEmpty()) {
        EmptyItemList()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            items(
                items = items,
                key = { it.id }
            ) { item ->
                InventoryItemCard(
                    item = item,
                    onClick = { onItemSelected(item) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun EmptyItemList() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Text(
            text = stringResource(id = R.string.no_items),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

