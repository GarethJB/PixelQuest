package com.jb.pixelquest.feature.studio.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jb.pixelquest.feature.studio.model.AssetCategory
import com.jb.pixelquest.shared.presentation.resources.R

/**
 * ?ì…‹ ì¹´í…Œê³ ë¦¬ ??ì»´í¬?ŒíŠ¸
 * State Hoisting: ? íƒ ?´ë²¤?¸ë§Œ ?ìœ„ë¡??„ë‹¬
 */
@Composable
fun AssetCategoryTabs(
    selectedCategory: AssetCategory?,
    onCategorySelected: (AssetCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    val categories = listOf(
        AssetCategory.TEMPLATE,
        AssetCategory.PALETTE,
        AssetCategory.BRUSH
    )

    ScrollableTabRow(
        selectedTabIndex = categories.indexOfFirst { it == selectedCategory }.takeIf { it >= 0 } ?: 0,
        modifier = modifier.fillMaxWidth()
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                selected = category == selectedCategory,
                onClick = { onCategorySelected(category) },
                text = {
                    Text(
                        text = when (category) {
                            AssetCategory.TEMPLATE -> stringResource(id = R.string.templates)
                            AssetCategory.PALETTE -> stringResource(id = R.string.palettes)
                            AssetCategory.BRUSH -> stringResource(id = R.string.brushes)
                        }
                    )
                }
            )
        }
    }
}

