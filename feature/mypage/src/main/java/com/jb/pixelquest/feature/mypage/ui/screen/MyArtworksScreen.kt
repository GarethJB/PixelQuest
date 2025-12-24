package com.jb.pixelquest.feature.mypage.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.feature.mypage.model.ArtworkFilterOption
import com.jb.pixelquest.feature.mypage.model.ArtworkSortOption
import com.jb.pixelquest.feature.mypage.model.MyPageAction
import com.jb.pixelquest.feature.mypage.model.MyPageUiState
import com.jb.pixelquest.feature.mypage.ui.component.ArtworkFilterChips
import com.jb.pixelquest.feature.mypage.ui.component.ArtworkSortMenu
import com.jb.pixelquest.feature.mypage.ui.component.MyArtworkGrid
import com.jb.pixelquest.shared.presentation.resources.R

@Composable
fun MyArtworksScreen(
    uiState: MyPageUiState,
    onAction: (MyPageAction) -> Unit,
    modifier: Modifier = Modifier
) {
    var sortMenuExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.my_artworks),
                style = MaterialTheme.typography.titleLarge
            )

            Box {
                IconButton(onClick = { sortMenuExpanded = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = stringResource(id = R.string.sort)
                    )
                }
                ArtworkSortMenu(
                    currentSortOption = uiState.sortOption,
                    onSortOptionSelected = { option ->
                        onAction(MyPageAction.SelectSortOption(option))
                    },
                    expanded = sortMenuExpanded,
                    onDismissRequest = { sortMenuExpanded = false }
                )
            }
        }

        ArtworkFilterChips(
            selectedFilter = uiState.filterOption,
            onFilterSelected = { filter ->
                onAction(MyPageAction.SelectFilterOption(filter))
            }
        )

        Box(modifier = Modifier.fillMaxSize()) {
            MyArtworkGrid(
                artworks = uiState.myArtworks,
                onArtworkSelected = { artwork ->
                    onAction(MyPageAction.SelectArtwork(artwork))
                },
                onEditClick = { artworkId ->

                },
                onDeleteClick = { artworkId ->
                    onAction(MyPageAction.DeleteArtwork(artworkId))
                },
                onToggleVisibilityClick = { artworkId ->
                    onAction(MyPageAction.ToggleArtworkVisibility(artworkId))
                }
            )

            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

