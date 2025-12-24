package com.jb.pixelquest.feature.gallery.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.feature.gallery.model.ArtworkCategory
import com.jb.pixelquest.shared.presentation.resources.R

@Composable
fun CategoryFilterChips(
    selectedCategory: ArtworkCategory?,
    onCategorySelected: (ArtworkCategory) -> Unit,
    onCategoryCleared: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(ArtworkCategory.values().toList()) { category ->
            FilterChip(
                selected = selectedCategory == category,
                onClick = {
                    if (selectedCategory == category) {
                        onCategoryCleared()
                    } else {
                        onCategorySelected(category)
                    }
                },
                label = {
                    Text(
                        text = getCategoryName(category),
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                shape = RoundedCornerShape(16.dp)
            )
        }
    }
}

@Composable
private fun getCategoryName(category: ArtworkCategory): String {
    return when (category) {
        ArtworkCategory.RETRO -> stringResource(id = R.string.category_retro)
        ArtworkCategory.FANTASY -> stringResource(id = R.string.category_fantasy)
        ArtworkCategory.CYBERPUNK -> stringResource(id = R.string.category_cyberpunk)
        ArtworkCategory.ANIMAL -> stringResource(id = R.string.category_animal)
        ArtworkCategory.CHARACTER -> stringResource(id = R.string.category_character)
        ArtworkCategory.LANDSCAPE -> stringResource(id = R.string.category_landscape)
        ArtworkCategory.OBJECT -> stringResource(id = R.string.category_object)
        ArtworkCategory.ICON -> stringResource(id = R.string.category_icon)
        ArtworkCategory.PATTERN -> stringResource(id = R.string.category_pattern)
        ArtworkCategory.ABSTRACT -> stringResource(id = R.string.category_abstract)
    }
}

