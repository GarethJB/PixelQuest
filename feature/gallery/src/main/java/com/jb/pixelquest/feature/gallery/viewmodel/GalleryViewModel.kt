package com.jb.pixelquest.feature.gallery.viewmodel

import androidx.lifecycle.ViewModel
import com.jb.pixelquest.feature.gallery.model.Artwork
import com.jb.pixelquest.feature.gallery.model.ArtworkCategory
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
    private val getTrendingArtworksUseCase: com.jb.pixelquest.shared.domain.usecase.artwork.GetTrendingArtworksUseCase,
    private val getLatestArtworksUseCase: com.jb.pixelquest.shared.domain.usecase.artwork.GetLatestArtworksUseCase,
    private val getCategoryArtworksUseCase: com.jb.pixelquest.shared.domain.usecase.artwork.GetCategoryArtworksUseCase,
    private val toggleLikeUseCase: com.jb.pixelquest.shared.domain.usecase.artwork.ToggleLikeUseCase,
    private val toggleBookmarkUseCase: com.jb.pixelquest.shared.domain.usecase.artwork.ToggleBookmarkUseCase,
    private val searchArtworksUseCase: com.jb.pixelquest.shared.domain.usecase.artwork.SearchArtworksUseCase,
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

        val trendingResult = getTrendingArtworksUseCase()
        val latestResult = getLatestArtworksUseCase()

        val trendingArtworks = trendingResult.getOrElse { emptyList() }.map { it.toGalleryArtwork() }
        val latestArtworks = latestResult.getOrElse { emptyList() }.map { it.toGalleryArtwork() }

        reduce {
            state.copy(
                isLoading = false,
                trendingArtworks = trendingArtworks,
                latestArtworks = latestArtworks,
                error = trendingResult.exceptionOrNull()?.message
                    ?: latestResult.exceptionOrNull()?.message
            )
        }
    }
    
    private fun com.jb.pixelquest.shared.domain.model.artwork.Artwork.toGalleryArtwork(): Artwork {
        return Artwork(
            id = this.id,
            title = this.title,
            description = this.description,
            author = com.jb.pixelquest.feature.gallery.model.User(
                id = this.author.id,
                username = this.author.username,
                avatarUrl = this.author.avatarUrl,
                level = this.author.level,
                isVerified = this.author.isVerified
            ),
            imageUrl = this.imageUrl,
            thumbnailUrl = this.thumbnailUrl,
            category = when (this.category) {
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.RETRO -> com.jb.pixelquest.feature.gallery.model.ArtworkCategory.RETRO
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.FANTASY -> com.jb.pixelquest.feature.gallery.model.ArtworkCategory.FANTASY
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.CYBERPUNK -> com.jb.pixelquest.feature.gallery.model.ArtworkCategory.CYBERPUNK
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.ANIMAL -> com.jb.pixelquest.feature.gallery.model.ArtworkCategory.ANIMAL
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.CHARACTER -> com.jb.pixelquest.feature.gallery.model.ArtworkCategory.CHARACTER
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.LANDSCAPE -> com.jb.pixelquest.feature.gallery.model.ArtworkCategory.LANDSCAPE
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.OBJECT -> com.jb.pixelquest.feature.gallery.model.ArtworkCategory.OBJECT
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.ICON -> com.jb.pixelquest.feature.gallery.model.ArtworkCategory.ICON
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.PATTERN -> com.jb.pixelquest.feature.gallery.model.ArtworkCategory.PATTERN
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.ABSTRACT -> com.jb.pixelquest.feature.gallery.model.ArtworkCategory.ABSTRACT
            },
            tags = this.tags,
            likes = this.likes,
            views = this.views,
            comments = this.comments,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt,
            isLiked = this.isLiked,
            isBookmarked = this.isBookmarked,
            canvasSize = androidx.compose.ui.unit.IntSize(
                this.canvasSize.width,
                this.canvasSize.height
            ),
            palette = this.palette?.map { color ->
                androidx.compose.ui.graphics.Color(
                    red = color.red,
                    green = color.green,
                    blue = color.blue,
                    alpha = color.alpha
                )
            },
            questId = this.questId,
            isCollaborative = this.isCollaborative,
            contributors = this.contributors.map { user ->
                com.jb.pixelquest.feature.gallery.model.User(
                    id = user.id,
                    username = user.username,
                    avatarUrl = user.avatarUrl,
                    level = user.level,
                    isVerified = user.isVerified
                )
            }
        )
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
                        selectedTab = GalleryTab.CATEGORY,
                        isLoading = true
                    )
                }

                val category = when (action.category) {
                    com.jb.pixelquest.feature.gallery.model.ArtworkCategory.RETRO -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.RETRO
                    com.jb.pixelquest.feature.gallery.model.ArtworkCategory.FANTASY -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.FANTASY
                    com.jb.pixelquest.feature.gallery.model.ArtworkCategory.CYBERPUNK -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.CYBERPUNK
                    com.jb.pixelquest.feature.gallery.model.ArtworkCategory.ANIMAL -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.ANIMAL
                    com.jb.pixelquest.feature.gallery.model.ArtworkCategory.CHARACTER -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.CHARACTER
                    com.jb.pixelquest.feature.gallery.model.ArtworkCategory.LANDSCAPE -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.LANDSCAPE
                    com.jb.pixelquest.feature.gallery.model.ArtworkCategory.OBJECT -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.OBJECT
                    com.jb.pixelquest.feature.gallery.model.ArtworkCategory.ICON -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.ICON
                    com.jb.pixelquest.feature.gallery.model.ArtworkCategory.PATTERN -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.PATTERN
                    com.jb.pixelquest.feature.gallery.model.ArtworkCategory.ABSTRACT -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.ABSTRACT
                }
                
                val result = getCategoryArtworksUseCase(category)
                val categoryArtworks = result.getOrElse { emptyList() }.map { it.toGalleryArtwork() }
                
                reduce {
                    state.copy(
                        isLoading = false,
                        categoryArtworks = categoryArtworks,
                        error = result.exceptionOrNull()?.message
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
                        val result = getTrendingArtworksUseCase()
                        val artworks = result.getOrElse { emptyList() }.map { it.toGalleryArtwork() }
                        reduce {
                            state.copy(
                                isLoading = false,
                                trendingArtworks = artworks,
                                error = result.exceptionOrNull()?.message
                            )
                        }
                    }
                    GalleryTab.LATEST -> {
                        val result = getLatestArtworksUseCase()
                        val artworks = result.getOrElse { emptyList() }.map { it.toGalleryArtwork() }
                        reduce {
                            state.copy(
                                isLoading = false,
                                latestArtworks = artworks,
                                error = result.exceptionOrNull()?.message
                            )
                        }
                    }
                    GalleryTab.CATEGORY -> {
                        if (state.selectedCategory != null) {
                            val category = when (val cat = state.selectedCategory) {
                                ArtworkCategory.RETRO -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.RETRO
                                ArtworkCategory.FANTASY -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.FANTASY
                                ArtworkCategory.CYBERPUNK -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.CYBERPUNK
                                ArtworkCategory.ANIMAL -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.ANIMAL
                                ArtworkCategory.CHARACTER -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.CHARACTER
                                ArtworkCategory.LANDSCAPE -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.LANDSCAPE
                                ArtworkCategory.OBJECT -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.OBJECT
                                ArtworkCategory.ICON -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.ICON
                                ArtworkCategory.PATTERN -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.PATTERN
                                ArtworkCategory.ABSTRACT -> com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.ABSTRACT
                                null -> TODO()
                            }
                            val result = getCategoryArtworksUseCase(category)
                            val artworks = result.getOrElse { emptyList() }.map { it.toGalleryArtwork() }
                            reduce {
                                state.copy(
                                    isLoading = false,
                                    categoryArtworks = artworks,
                                    error = result.exceptionOrNull()?.message
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

                    val result = searchArtworksUseCase(state.searchQuery)
                    val searchResults = result.getOrElse { emptyList() }.map { it.toGalleryArtwork() }
                    
                    reduce {
                        state.copy(
                            isLoading = false,
                            searchResults = searchResults,
                            error = result.exceptionOrNull()?.message
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
                    val result = toggleLikeUseCase(action.artworkId)
                    result.onSuccess { isLiked ->
                        val updatedArtwork = artwork.copy(
                            isLiked = isLiked,
                            likes = if (isLiked) artwork.likes + 1 else artwork.likes - 1
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
                                if (isLiked) "좋아요를 추가했습니다" else "좋아요를 취소했습니다"
                            )
                        )
                    }.onFailure { exception ->
                        postSideEffect(
                            GallerySideEffect.ShowSnackbar(exception.message ?: "좋아요 처리에 실패했습니다")
                        )
                    }
                }
            }

            is GalleryAction.ToggleBookmark -> {
                val artwork = findArtworkById(action.artworkId)
                if (artwork != null) {
                    val result = toggleBookmarkUseCase(action.artworkId)
                    result.onSuccess { isBookmarked ->
                        val updatedArtwork = artwork.copy(isBookmarked = isBookmarked)

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
                                if (isBookmarked) "북마크에 추가했습니다" else "북마크에서 제거했습니다"
                            )
                        )
                    }.onFailure { exception ->
                        postSideEffect(
                            GallerySideEffect.ShowSnackbar(exception.message ?: "북마크 처리에 실패했습니다")
                        )
                    }
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

