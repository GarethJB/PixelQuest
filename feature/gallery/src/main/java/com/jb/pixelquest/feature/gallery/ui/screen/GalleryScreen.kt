package com.jb.pixelquest.feature.gallery.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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

/**
 * Gallery 메인 ?�면
 * State Hoisting ?�턴: ?�태???�위?�서 관리하�? ?�션�??�달받음
 */
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
            // ????
            GalleryTabRow(
                selectedTab = uiState.selectedTab,
                onTabSelected = { tab ->
                    onAction(GalleryAction.SelectTab(tab))
                }
            )

            // 카테고리 ?�터 (카테고리 ??�� ?�만 ?�시)
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

            // ?�품 그리??
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

                // 로딩 ?�디케?�터
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Center)
                    )
                }
            }
        }
    }

    // ?�품 ?�세 ?�이?�로�?
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

