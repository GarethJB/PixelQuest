package com.jb.pixelquest.feature.gallery.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment.Companion.Center
import com.jb.pixelquest.feature.gallery.model.GalleryAction
import com.jb.pixelquest.feature.gallery.model.GalleryTab
import com.jb.pixelquest.feature.gallery.model.GalleryUiState
import com.jb.pixelquest.feature.gallery.ui.component.ArtworkGrid
import com.jb.pixelquest.feature.gallery.ui.component.CategoryFilterChips
import com.jb.pixelquest.feature.gallery.ui.component.GalleryTabRow
import com.jb.pixelquest.feature.gallery.ui.screen.ArtworkDetailScreen
import com.jb.pixelquest.presentation.component.ScreenHeader
import com.jb.pixelquest.presentation.resources.R

/**
 * Gallery 메인 화면
 * State Hoisting 패턴: 상태는 상위에서 관리하고, 액션만 전달받음
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
            // 탭 행
            GalleryTabRow(
                selectedTab = uiState.selectedTab,
                onTabSelected = { tab ->
                    onAction(GalleryAction.SelectTab(tab))
                }
            )

            // 카테고리 필터 (카테고리 탭일 때만 표시)
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

            // 작품 그리드
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

                // 로딩 인디케이터
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Center)
                    )
                }
            }
        }
    }

    // 작품 상세 다이얼로그
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

