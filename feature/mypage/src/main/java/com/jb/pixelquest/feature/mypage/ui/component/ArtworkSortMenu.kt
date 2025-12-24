package com.jb.pixelquest.feature.mypage.ui.component

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jb.pixelquest.feature.mypage.model.ArtworkSortOption
import com.jb.pixelquest.shared.presentation.resources.R

@Composable
fun ArtworkSortMenu(
    currentSortOption: ArtworkSortOption,
    onSortOptionSelected: (ArtworkSortOption) -> Unit,
    expanded: Boolean,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        ArtworkSortOption.values().forEach { option ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = getSortOptionText(option),
                        style = if (currentSortOption == option) {
                            MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.primary
                            )
                        } else {
                            MaterialTheme.typography.bodyMedium
                        }
                    )
                },
                onClick = {
                    onSortOptionSelected(option)
                    onDismissRequest()
                }
            )
        }
    }
}

@Composable
private fun getSortOptionText(option: ArtworkSortOption): String {
    return when (option) {
        ArtworkSortOption.LATEST -> stringResource(id = R.string.sort_latest)
        ArtworkSortOption.OLDEST -> stringResource(id = R.string.sort_oldest)
        ArtworkSortOption.MOST_LIKED -> stringResource(id = R.string.sort_most_liked)
        ArtworkSortOption.MOST_VIEWED -> stringResource(id = R.string.sort_most_viewed)
    }
}

