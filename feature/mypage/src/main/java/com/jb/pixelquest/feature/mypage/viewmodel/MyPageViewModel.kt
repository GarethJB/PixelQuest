package com.jb.pixelquest.feature.mypage.viewmodel

import androidx.lifecycle.ViewModel
import com.jb.pixelquest.feature.mypage.model.Artwork
import com.jb.pixelquest.feature.mypage.model.ArtworkFilterOption
import com.jb.pixelquest.feature.mypage.model.ArtworkSortOption
import com.jb.pixelquest.feature.mypage.model.MyPageAction
import com.jb.pixelquest.feature.mypage.model.MyPageSideEffect
import com.jb.pixelquest.feature.mypage.model.MyPageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val getMyArtworksUseCase: com.jb.pixelquest.shared.domain.usecase.artwork.GetMyArtworksUseCase,
    private val deleteArtworkUseCase: com.jb.pixelquest.shared.domain.usecase.artwork.DeleteArtworkUseCase,
    private val toggleArtworkVisibilityUseCase: com.jb.pixelquest.shared.domain.usecase.artwork.ToggleArtworkVisibilityUseCase,
) : ContainerHost<MyPageUiState, MyPageSideEffect>, ViewModel() {

    override val container = container<MyPageUiState, MyPageSideEffect>(
        MyPageUiState()
    ) {
        loadInitialData()
    }

    private fun loadInitialData() = intent {
        reduce {
            state.copy(isLoading = true)
        }

        val result = getMyArtworksUseCase()
        val artworks = result.getOrElse { emptyList() }.map { it.toMyPageArtwork() }

        reduce {
            state.copy(
                isLoading = false,
                myArtworks = artworks,
                draftArtworks = artworks.filter { it.isDraft },
                publishedArtworks = artworks.filter { it.isPublished },
                error = result.exceptionOrNull()?.message
            )
        }
    }
    
    private fun com.jb.pixelquest.shared.domain.model.artwork.Artwork.toMyPageArtwork(): Artwork {
        return Artwork(
            id = this.id,
            title = this.title,
            description = this.description,
            imageUrl = this.imageUrl,
            thumbnailUrl = this.thumbnailUrl,
            category = when (this.category) {
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.RETRO -> com.jb.pixelquest.feature.mypage.model.ArtworkCategory.RETRO
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.FANTASY -> com.jb.pixelquest.feature.mypage.model.ArtworkCategory.FANTASY
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.CYBERPUNK -> com.jb.pixelquest.feature.mypage.model.ArtworkCategory.CYBERPUNK
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.ANIMAL -> com.jb.pixelquest.feature.mypage.model.ArtworkCategory.ANIMAL
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.CHARACTER -> com.jb.pixelquest.feature.mypage.model.ArtworkCategory.CHARACTER
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.LANDSCAPE -> com.jb.pixelquest.feature.mypage.model.ArtworkCategory.LANDSCAPE
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.OBJECT -> com.jb.pixelquest.feature.mypage.model.ArtworkCategory.OBJECT
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.ICON -> com.jb.pixelquest.feature.mypage.model.ArtworkCategory.ICON
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.PATTERN -> com.jb.pixelquest.feature.mypage.model.ArtworkCategory.PATTERN
                com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory.ABSTRACT -> com.jb.pixelquest.feature.mypage.model.ArtworkCategory.ABSTRACT
            },
            tags = this.tags,
            likes = this.likes,
            views = this.views,
            comments = this.comments,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt,
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
            isPublished = this.isPublished,
            isDraft = this.isDraft
        )
    }

    fun handleAction(action: MyPageAction) = intent {
        when (action) {
            is MyPageAction.SelectTab -> {
                reduce {
                    state.copy(selectedTab = action.tab)
                }
            }

            is MyPageAction.SelectArtwork -> {
                reduce {
                    state.copy(
                        selectedArtwork = action.artwork,
                        showArtworkDetail = true
                    )
                }
            }

            is MyPageAction.HideArtworkDetail -> {
                reduce {
                    state.copy(
                        showArtworkDetail = false,
                        selectedArtwork = null
                    )
                }
            }

            is MyPageAction.DeleteArtwork -> {
                val artwork = findArtworkById(action.artworkId)
                if (artwork != null) {
                    reduce {
                        state.copy(isLoading = true)
                    }
                    
                    val result = deleteArtworkUseCase(action.artworkId)
                    result.onSuccess {
                        reduce {
                            state.copy(
                                isLoading = false,
                                myArtworks = state.myArtworks.filter { it.id != action.artworkId },
                                draftArtworks = state.draftArtworks.filter { it.id != action.artworkId },
                                publishedArtworks = state.publishedArtworks.filter { it.id != action.artworkId },
                                showArtworkDetail = false,
                                selectedArtwork = null
                            )
                        }

                        postSideEffect(MyPageSideEffect.ShowSnackbar("작품을 삭제했습니다"))
                    }.onFailure { exception ->
                        reduce {
                            state.copy(
                                isLoading = false,
                                error = exception.message
                            )
                        }
                        postSideEffect(MyPageSideEffect.ShowSnackbar(exception.message ?: "작품 삭제에 실패했습니다"))
                    }
                }
            }

            is MyPageAction.ToggleArtworkVisibility -> {
                val artwork = findArtworkById(action.artworkId)
                if (artwork != null) {
                    reduce {
                        state.copy(isLoading = true)
                    }
                    
                    val result = toggleArtworkVisibilityUseCase(action.artworkId)
                    result.onSuccess { isPublished ->
                        val updatedArtwork = artwork.copy(
                            isPublished = isPublished,
                            isDraft = !isPublished
                        )

                        reduce {
                            state.copy(
                                isLoading = false,
                                myArtworks = updateArtwork(state.myArtworks, updatedArtwork),
                                draftArtworks = if (isPublished) {
                                    state.draftArtworks.filter { it.id != action.artworkId }
                                } else {
                                    updateArtwork(state.draftArtworks, updatedArtwork)
                                },
                                publishedArtworks = if (isPublished) {
                                    updateArtwork(state.publishedArtworks, updatedArtwork)
                                } else {
                                    state.publishedArtworks.filter { it.id != action.artworkId }
                                },
                                selectedArtwork = if (state.selectedArtwork?.id == action.artworkId) {
                                    updatedArtwork
                                } else {
                                    state.selectedArtwork
                                }
                            )
                        }

                        postSideEffect(
                            MyPageSideEffect.ShowSnackbar(
                                if (isPublished) "작품을 공개했습니다" else "작품을 비공개로 전환했습니다"
                            )
                        )
                    }.onFailure { exception ->
                        reduce {
                            state.copy(
                                isLoading = false,
                                error = exception.message
                            )
                        }
                        postSideEffect(MyPageSideEffect.ShowSnackbar(exception.message ?: "공개 상태 변경에 실패했습니다"))
                    }
                }
            }

            is MyPageAction.SelectSortOption -> {
                reduce {
                    state.copy(sortOption = action.option)
                }
                applySortAndFilter()
            }

            is MyPageAction.SelectFilterOption -> {
                reduce {
                    state.copy(filterOption = action.option)
                }
                applySortAndFilter()
            }

            is MyPageAction.RefreshMyArtworks -> {
                reduce {
                    state.copy(isLoading = true)
                }

                val result = getMyArtworksUseCase()
                val artworks = result.getOrElse { emptyList() }.map { it.toMyPageArtwork() }

                reduce {
                    state.copy(
                        isLoading = false,
                        myArtworks = artworks,
                        draftArtworks = artworks.filter { it.isDraft },
                        publishedArtworks = artworks.filter { it.isPublished },
                        error = result.exceptionOrNull()?.message
                    )
                }
                applySortAndFilter()
            }

            is MyPageAction.ShowError -> {
                reduce {
                    state.copy(error = action.message)
                }
                postSideEffect(MyPageSideEffect.ShowSnackbar(action.message))
            }

            is MyPageAction.ClearError -> {
                reduce {
                    state.copy(error = null)
                }
            }

            is MyPageAction.SelectCategory,
            is MyPageAction.SelectItem,
            is MyPageAction.HideItemDetail,
            is MyPageAction.EquipItem,
            is MyPageAction.UnequipItem -> {

            }
        }
    }

    private fun findArtworkById(artworkId: String): Artwork? {
        val state = container.stateFlow.value
        return state.myArtworks.find { it.id == artworkId }
            ?: state.draftArtworks.find { it.id == artworkId }
            ?: state.publishedArtworks.find { it.id == artworkId }
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

    private fun applySortAndFilter() = intent {
        val state = container.stateFlow.value
        var filtered = state.myArtworks

        when (state.filterOption) {
            ArtworkFilterOption.PUBLISHED -> {
                filtered = filtered.filter { it.isPublished }
            }
            ArtworkFilterOption.DRAFT -> {
                filtered = filtered.filter { it.isDraft }
            }
            ArtworkFilterOption.QUEST_RELATED -> {
                filtered = filtered.filter { it.questId != null }
            }
            null -> {

            }
        }

        val sorted = when (state.sortOption) {
            ArtworkSortOption.LATEST -> filtered.sortedByDescending { it.createdAt }
            ArtworkSortOption.OLDEST -> filtered.sortedBy { it.createdAt }
            ArtworkSortOption.MOST_LIKED -> filtered.sortedByDescending { it.likes }
            ArtworkSortOption.MOST_VIEWED -> filtered.sortedByDescending { it.views }
        }

        reduce {
            state.copy(myArtworks = sorted)
        }
    }
}

