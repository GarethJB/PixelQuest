package com.jb.pixelquest.feature.gallery.viewmodel

import androidx.lifecycle.ViewModel
import com.jb.pixelquest.feature.gallery.model.Artwork
import com.jb.pixelquest.feature.gallery.model.GalleryAction
import com.jb.pixelquest.feature.gallery.model.GallerySideEffect
import com.jb.pixelquest.feature.gallery.model.GalleryTab
import com.jb.pixelquest.feature.gallery.model.GalleryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 * Gallery 화면 ViewModel
 * Orbit MVI 패턴 사용
 */
@HiltViewModel
class GalleryViewModel @Inject constructor(
    // TODO: UseCase 주입
    // private val getTrendingArtworksUseCase: GetTrendingArtworksUseCase,
    // private val getLatestArtworksUseCase: GetLatestArtworksUseCase,
    // private val getCategoryArtworksUseCase: GetCategoryArtworksUseCase,
    // private val toggleLikeUseCase: ToggleLikeUseCase,
    // private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
    // private val searchArtworksUseCase: SearchArtworksUseCase,
) : ContainerHost<GalleryUiState, GallerySideEffect>, ViewModel() {

    override val container = container<GalleryUiState, GallerySideEffect>(
        GalleryUiState()
    ) {
        // 초기 데이터 로드
        loadInitialData()
    }

    /**
     * 초기 데이터 로드
     */
    private fun loadInitialData() = intent {
        reduce {
            state.copy(isLoading = true)
        }

        // TODO: UseCase를 통한 데이터 로드
        // val trendingArtworks = getTrendingArtworksUseCase()
        // val latestArtworks = getLatestArtworksUseCase()

        // 임시 데이터 (개발용)
        val mockTrendingArtworks = emptyList<Artwork>()
        val mockLatestArtworks = emptyList<Artwork>()

        reduce {
            state.copy(
                isLoading = false,
                trendingArtworks = mockTrendingArtworks,
                latestArtworks = mockLatestArtworks
            )
        }
    }

    /**
     * 액션 처리
     */
    fun handleAction(action: GalleryAction) = intent {
        when (action) {
            is GalleryAction.SelectTab -> {
                reduce {
                    state.copy(selectedTab = action.tab)
                }
                
                // 탭별 데이터 로드
                when (action.tab) {
                    GalleryTab.TRENDING -> {
                        // TODO: 인기 작품 로드
                    }
                    GalleryTab.LATEST -> {
                        // TODO: 최신 작품 로드
                    }
                    GalleryTab.CATEGORY -> {
                        // 카테고리 선택 필요
                    }
                }
            }

            is GalleryAction.SelectArtwork -> {
                reduce {
                    state.copy(
                        selectedArtwork = action.artwork,
                        showArtworkDetail = true
                    )
                }
            }

            is GalleryAction.HideArtworkDetail -> {
                reduce {
                    state.copy(
                        showArtworkDetail = false,
                        selectedArtwork = null
                    )
                }
            }

            is GalleryAction.SelectCategory -> {
                reduce {
                    state.copy(
                        selectedCategory = action.category,
                        selectedTab = GalleryTab.CATEGORY
                    )
                }
                
                // TODO: 카테고리별 작품 로드
                // val categoryArtworks = getCategoryArtworksUseCase(action.category)
                reduce {
                    state.copy(
                        categoryArtworks = emptyList() // TODO: 실제 데이터로 교체
                    )
                }
            }

            is GalleryAction.ClearCategory -> {
                reduce {
                    state.copy(
                        selectedCategory = null,
                        categoryArtworks = emptyList()
                    )
                }
            }

            is GalleryAction.RefreshArtworks -> {
                reduce {
                    state.copy(isLoading = true)
                }

                // TODO: UseCase를 통한 새로고침
                when (state.selectedTab) {
                    GalleryTab.TRENDING -> {
                        // val artworks = getTrendingArtworksUseCase()
                        reduce {
                            state.copy(
                                isLoading = false,
                                trendingArtworks = emptyList() // TODO: 실제 데이터로 교체
                            )
                        }
                    }
                    GalleryTab.LATEST -> {
                        // val artworks = getLatestArtworksUseCase()
                        reduce {
                            state.copy(
                                isLoading = false,
                                latestArtworks = emptyList() // TODO: 실제 데이터로 교체
                            )
                        }
                    }
                    GalleryTab.CATEGORY -> {
                        if (state.selectedCategory != null) {
                            // val artworks = getCategoryArtworksUseCase(state.selectedCategory)
                            reduce {
                                state.copy(
                                    isLoading = false,
                                    categoryArtworks = emptyList() // TODO: 실제 데이터로 교체
                                )
                            }
                        }
                    }
                }
            }

            is GalleryAction.LoadMoreArtworks -> {
                // TODO: 페이지네이션 구현
            }

            is GalleryAction.UpdateSearchQuery -> {
                reduce {
                    state.copy(searchQuery = action.query)
                }
            }

            is GalleryAction.PerformSearch -> {
                if (state.searchQuery.isNotBlank()) {
                    reduce {
                        state.copy(isLoading = true, isSearchActive = true)
                    }

                    // TODO: UseCase를 통한 검색
                    // val results = searchArtworksUseCase(state.searchQuery)
                    reduce {
                        state.copy(
                            isLoading = false,
                            searchResults = emptyList() // TODO: 실제 데이터로 교체
                        )
                    }
                }
            }

            is GalleryAction.ClearSearch -> {
                reduce {
                    state.copy(
                        searchQuery = "",
                        searchResults = emptyList(),
                        isSearchActive = false
                    )
                }
            }

            is GalleryAction.ToggleSearch -> {
                reduce {
                    state.copy(isSearchActive = !state.isSearchActive)
                }
                if (!state.isSearchActive) {
//                    handleAction(GalleryAction.ClearSearch)
                }
            }

            is GalleryAction.ToggleLike -> {
                val artwork = findArtworkById(action.artworkId)
                if (artwork != null) {
                    // TODO: UseCase를 통한 좋아요 토글
                    // toggleLikeUseCase(action.artworkId)
                    
                    val updatedArtwork = artwork.copy(
                        isLiked = !artwork.isLiked,
                        likes = if (artwork.isLiked) artwork.likes - 1 else artwork.likes + 1
                    )

                    // 상태 업데이트
                    reduce {
                        state.copy(
                            trendingArtworks = updateArtwork(state.trendingArtworks, updatedArtwork),
                            latestArtworks = updateArtwork(state.latestArtworks, updatedArtwork),
                            categoryArtworks = updateArtwork(state.categoryArtworks, updatedArtwork),
                            searchResults = updateArtwork(state.searchResults, updatedArtwork),
                            selectedArtwork = if (state.selectedArtwork?.id == action.artworkId) {
                                updatedArtwork
                            } else {
                                state.selectedArtwork
                            }
                        )
                    }

                    postSideEffect(
                        GallerySideEffect.ShowSnackbar(
                            if (updatedArtwork.isLiked) "좋아요를 눌렀습니다" else "좋아요를 취소했습니다"
                        )
                    )
                }
            }

            is GalleryAction.ToggleBookmark -> {
                val artwork = findArtworkById(action.artworkId)
                if (artwork != null) {
                    // TODO: UseCase를 통한 북마크 토글
                    // toggleBookmarkUseCase(action.artworkId)
                    
                    val updatedArtwork = artwork.copy(isBookmarked = !artwork.isBookmarked)

                    // 상태 업데이트
                    reduce {
                        state.copy(
                            trendingArtworks = updateArtwork(state.trendingArtworks, updatedArtwork),
                            latestArtworks = updateArtwork(state.latestArtworks, updatedArtwork),
                            categoryArtworks = updateArtwork(state.categoryArtworks, updatedArtwork),
                            searchResults = updateArtwork(state.searchResults, updatedArtwork),
                            selectedArtwork = if (state.selectedArtwork?.id == action.artworkId) {
                                updatedArtwork
                            } else {
                                state.selectedArtwork
                            }
                        )
                    }

                    postSideEffect(
                        GallerySideEffect.ShowSnackbar(
                            if (updatedArtwork.isBookmarked) "북마크에 추가했습니다" else "북마크에서 제거했습니다"
                        )
                    )
                }
            }

            is GalleryAction.ShareArtwork -> {
                val artwork = findArtworkById(action.artworkId)
                if (artwork != null) {
                    postSideEffect(GallerySideEffect.ShareArtwork(artwork))
                }
            }

            is GalleryAction.ShowError -> {
                reduce {
                    state.copy(error = action.message)
                }
                postSideEffect(GallerySideEffect.ShowSnackbar(action.message))
            }

            is GalleryAction.ClearError -> {
                reduce {
                    state.copy(error = null)
                }
            }
        }
    }

    /**
     * 작품 ID로 작품 찾기
     */
    private fun findArtworkById(artworkId: String): Artwork? {
        val state = container.stateFlow.value
        return state.trendingArtworks.find { it.id == artworkId }
            ?: state.latestArtworks.find { it.id == artworkId }
            ?: state.categoryArtworks.find { it.id == artworkId }
            ?: state.searchResults.find { it.id == artworkId }
            ?: state.selectedArtwork?.takeIf { it.id == artworkId }
    }

    /**
     * 작품 목록에서 작품 업데이트
     */
    private fun updateArtwork(
        artworks: List<Artwork>,
        updatedArtwork: Artwork
    ): List<Artwork> {
        return artworks.map { artwork ->
            if (artwork.id == updatedArtwork.id) {
                updatedArtwork
            } else {
                artwork
            }
        }
    }
}

