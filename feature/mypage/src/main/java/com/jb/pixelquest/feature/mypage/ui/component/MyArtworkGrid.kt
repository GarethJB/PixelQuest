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
import com.jb.pixelquest.feature.mypage.model.Artwork
import com.jb.pixelquest.shared.presentation.resources.R

@Composable
fun MyArtworkGrid(
    artworks: List<Artwork>,
    onArtworkSelected: (Artwork) -> Unit,
    onEditClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit,
    onToggleVisibilityClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (artworks.isEmpty()) {
        EmptyArtworkList()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            items(
                items = artworks,
                key = { it.id }
            ) { artwork ->
                MyArtworkCard(
                    artwork = artwork,
                    onClick = { onArtworkSelected(artwork) },
                    onEditClick = { onEditClick(artwork.id) },
                    onDeleteClick = { onDeleteClick(artwork.id) },
                    onToggleVisibilityClick = { onToggleVisibilityClick(artwork.id) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun EmptyArtworkList() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Text(
            text = stringResource(id = R.string.no_artworks),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

