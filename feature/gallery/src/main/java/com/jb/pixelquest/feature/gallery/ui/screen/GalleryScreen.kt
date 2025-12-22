package com.jb.pixelquest.feature.gallery.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import com.jb.pixelquest.feature.gallery.model.GalleryAction
import com.jb.pixelquest.feature.gallery.model.GalleryTab
import com.jb.pixelquest.feature.gallery.model.GalleryUiState
import com.jb.pixelquest.feature.gallery.ui.component.ArtworkGrid
import com.jb.pixelquest.feature.gallery.ui.component.CategoryFilterChips
import com.jb.pixelquest.feature.gallery.ui.component.GalleryTabRow
import com.jb.pixelquest.presentation.component.ScreenHeader
import com.jb.pixelquest.shared.presentation.resources.R


@Composable
fun GalleryScreen(
    uiState: GalleryUiState,
    onAction: (GalleryAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            ScreenHeader(titleResId = R.string.gallery_title)
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            GalleryTabRow(
                selectedTab = uiState.selectedTab,
                onTabSelected = { tab ->
                    onAction(GalleryAction.SelectTab(tab))
                }
            )

            if (uiState.selectedTab == GalleryTab.CATEGORY) {
                CategoryFilterChips(
                    selectedCategory = uiState.selectedCategory,
                    onCategorySelected = { category ->
                        onAction(GalleryAction.SelectCategory(category))
                    },
                    onCategoryCleared = {
                        onAction(GalleryAction.ClearCategory)
                    }
                )
            }

            Box(modifier = Modifier.fillMaxSize()) {
                val artworks = when (uiState.selectedTab) {
                    GalleryTab.TRENDING -> uiState.trendingArtworks
                    GalleryTab.LATEST -> uiState.latestArtworks
                    GalleryTab.CATEGORY -> uiState.categoryArtworks
                }

                ArtworkGrid(
                    artworks = artworks,
                    onArtworkSelected = { artwork ->
                        onAction(GalleryAction.SelectArtwork(artwork))
                    },
                    onLikeClick = { artworkId ->
                        onAction(GalleryAction.ToggleLike(artworkId))
                    },
                    onBookmarkClick = { artworkId ->
                        onAction(GalleryAction.ToggleBookmark(artworkId))
                    }
                )

                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Center)
                    )
                }
            }
        }
    }

    if (uiState.showArtworkDetail && uiState.selectedArtwork != null) {
        ArtworkDetailScreen(
            artwork = uiState.selectedArtwork,
            onLikeClick = {
                onAction(GalleryAction.ToggleLike(uiState.selectedArtwork.id))
            },
            onBookmarkClick = {
                onAction(GalleryAction.ToggleBookmark(uiState.selectedArtwork.id))
            },
            onShareClick = {
                onAction(GalleryAction.ShareArtwork(uiState.selectedArtwork.id))
            },
            onDismiss = {
                onAction(GalleryAction.HideArtworkDetail)
            }
        )
    }
}

