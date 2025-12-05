package com.jb.pixelquest.feature.mypage.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.feature.mypage.model.InventoryCategory
import com.jb.pixelquest.shared.presentation.resources.R

/**
 * ?¸ë²¤? ë¦¬ ì¹´í…Œê³ ë¦¬ ??
 * State Hoisting: ? íƒ ?´ë²¤?¸ë§Œ ?ìœ„ë¡??„ë‹¬
 */
@Composable
fun InventoryCategoryTabs(
    selectedCategory: InventoryCategory?,
    onCategorySelected: (InventoryCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    ScrollableTabRow(
        selectedTabIndex = selectedCategory?.let { InventoryCategory.values().indexOf(it) } ?: 0,
        modifier = modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        edgePadding = 16.dp
    ) {
        InventoryCategory.values().forEach { category ->
            Tab(
                selected = selectedCategory == category,
                onClick = { onCategorySelected(category) },
                text = {
                    Text(
                        text = getCategoryText(category)
                    )
                }
            )
        }
    }
}

@Composable
private fun getCategoryText(category: InventoryCategory): String {
    return when (category) {
        InventoryCategory.PALETTE -> stringResource(id = R.string.inventory_palette)
        InventoryCategory.BRUSH -> stringResource(id = R.string.inventory_brush)
        InventoryCategory.BADGE -> stringResource(id = R.string.inventory_badge)
        InventoryCategory.PROFILE_DECORATION -> stringResource(id = R.string.inventory_profile_decoration)
    }
}

