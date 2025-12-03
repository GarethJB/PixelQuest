package com.jb.pixelquest.feature.mypage.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.feature.mypage.model.ArtworkFilterOption
import com.jb.pixelquest.presentation.resources.R

/**
 * 작품 필터 칩
 * State Hoisting: 선택 이벤트만 상위로 전달
 */
@Composable
fun ArtworkFilterChips(
    selectedFilter: ArtworkFilterOption?,
    onFilterSelected: (ArtworkFilterOption?) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // 전체 필터 (선택 해제)
        item {
            FilterChip(
                selected = selectedFilter == null,
                onClick = { onFilterSelected(null) },
                label = {
                    Text(
                        text = stringResource(id = R.string.filter),
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                shape = RoundedCornerShape(16.dp)
            )
        }

        items(ArtworkFilterOption.values().toList()) { filter ->
            FilterChip(
                selected = selectedFilter == filter,
                onClick = {
                    if (selectedFilter == filter) {
                        onFilterSelected(null)
                    } else {
                        onFilterSelected(filter)
                    }
                },
                label = {
                    Text(
                        text = getFilterText(filter),
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                shape = RoundedCornerShape(16.dp)
            )
        }
    }
}

@Composable
private fun getFilterText(filter: ArtworkFilterOption): String {
    return when (filter) {
        ArtworkFilterOption.PUBLISHED -> stringResource(id = R.string.filter_published)
        ArtworkFilterOption.DRAFT -> stringResource(id = R.string.filter_draft)
        ArtworkFilterOption.QUEST_RELATED -> stringResource(id = R.string.filter_quest_related)
    }
}

