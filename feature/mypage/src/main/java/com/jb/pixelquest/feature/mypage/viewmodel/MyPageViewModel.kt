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
    // TODO: UseCase 주입
    // private val getMyArtworksUseCase: GetMyArtworksUseCase,
    // private val deleteArtworkUseCase: DeleteArtworkUseCase,
    // private val toggleArtworkVisibilityUseCase: ToggleArtworkVisibilityUseCase,
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

        // val artworks = getMyArtworksUseCase()

        val mockArtworks = emptyList<Artwork>()

        reduce {
            state.copy(
                isLoading = false,
                myArtworks = mockArtworks,
                draftArtworks = mockArtworks.filter { it.isDraft },
                publishedArtworks = mockArtworks.filter { it.isPublished }
            )
        }
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
                    // deleteArtworkUseCase(action.artworkId)

                    reduce {
                        state.copy(
                            myArtworks = state.myArtworks.filter { it.id != action.artworkId },
                            draftArtworks = state.draftArtworks.filter { it.id != action.artworkId },
                            publishedArtworks = state.publishedArtworks.filter { it.id != action.artworkId },
                            showArtworkDetail = false,
                            selectedArtwork = null
                        )
                    }

                    postSideEffect(MyPageSideEffect.ShowSnackbar(""))
                }
            }

            is MyPageAction.ToggleArtworkVisibility -> {
                val artwork = findArtworkById(action.artworkId)
                if (artwork != null) {
                    // toggleArtworkVisibilityUseCase(action.artworkId)

                    val updatedArtwork = artwork.copy(isPublished = !artwork.isPublished)

                    reduce {
                        state.copy(
                            myArtworks = updateArtwork(state.myArtworks, updatedArtwork),
                            draftArtworks = if (updatedArtwork.isPublished) {
                                state.draftArtworks.filter { it.id != action.artworkId }
                            } else {
                                updateArtwork(state.draftArtworks, updatedArtwork)
                            },
                            publishedArtworks = if (updatedArtwork.isPublished) {
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
                            if (updatedArtwork.isPublished) "" else ""
                        )
                    )
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

                // val artworks = getMyArtworksUseCase()

                reduce {
                    state.copy(
                        isLoading = false,
                        myArtworks = emptyList()
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

