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
        loadInitialData()
    }

    private fun loadInitialData() = intent {
        reduce {
            state.copy(isLoading = true)
        }

        // val trendingArtworks = getTrendingArtworksUseCase()
        // val latestArtworks = getLatestArtworksUseCase()

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

    fun handleAction(action: GalleryAction) = intent {
        when (action) {
            is GalleryAction.SelectTab -> {
                reduce {
                    state.copy(selectedTab = action.tab)
                }
                

                when (action.tab) {
                    GalleryTab.TRENDING -> {

                    }
                    GalleryTab.LATEST -> {

                    }
                    GalleryTab.CATEGORY -> {

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

                // val categoryArtworks = getCategoryArtworksUseCase(action.category)
                reduce {
                    state.copy(
                        categoryArtworks = emptyList()
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

                when (state.selectedTab) {
                    GalleryTab.TRENDING -> {
                        // val artworks = getTrendingArtworksUseCase()
                        reduce {
                            state.copy(
                                isLoading = false,
                                trendingArtworks = emptyList()
                            )
                        }
                    }
                    GalleryTab.LATEST -> {
                        // val artworks = getLatestArtworksUseCase()
                        reduce {
                            state.copy(
                                isLoading = false,
                                latestArtworks = emptyList()
                            )
                        }
                    }
                    GalleryTab.CATEGORY -> {
                        if (state.selectedCategory != null) {
                            // val artworks = getCategoryArtworksUseCase(state.selectedCategory)
                            reduce {
                                state.copy(
                                    isLoading = false,
                                    categoryArtworks = emptyList()
                                )
                            }
                        }
                    }
                }
            }

            is GalleryAction.LoadMoreArtworks -> {

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

                    // val results = searchArtworksUseCase(state.searchQuery)
                    reduce {
                        state.copy(
                            isLoading = false,
                            searchResults = emptyList()
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
                    // toggleLikeUseCase(action.artworkId)
                    
                    val updatedArtwork = artwork.copy(
                        isLiked = !artwork.isLiked,
                        likes = if (artwork.isLiked) artwork.likes - 1 else artwork.likes + 1
                    )

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
                            if (updatedArtwork.isLiked) "" else ""
                        )
                    )
                }
            }

            is GalleryAction.ToggleBookmark -> {
                val artwork = findArtworkById(action.artworkId)
                if (artwork != null) {
                    // toggleBookmarkUseCase(action.artworkId)
                    
                    val updatedArtwork = artwork.copy(isBookmarked = !artwork.isBookmarked)

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
                            if (updatedArtwork.isBookmarked) "" else ""
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

    private fun findArtworkById(artworkId: String): Artwork? {
        val state = container.stateFlow.value
        return state.trendingArtworks.find { it.id == artworkId }
            ?: state.latestArtworks.find { it.id == artworkId }
            ?: state.categoryArtworks.find { it.id == artworkId }
            ?: state.searchResults.find { it.id == artworkId }
            ?: state.selectedArtwork?.takeIf { it.id == artworkId }
    }

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

