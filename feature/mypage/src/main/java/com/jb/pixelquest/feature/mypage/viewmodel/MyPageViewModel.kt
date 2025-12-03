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

/**
 * MyPage 화면 ViewModel
 * Orbit MVI 패턴 사용
 */
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
        // val artworks = getMyArtworksUseCase()

        // 임시 데이터 (개발용)
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

    /**
     * 액션 처리
     */
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
                    // TODO: UseCase를 통한 작품 삭제
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

                    postSideEffect(MyPageSideEffect.ShowSnackbar("작품이 삭제되었습니다"))
                }
            }

            is MyPageAction.ToggleArtworkVisibility -> {
                val artwork = findArtworkById(action.artworkId)
                if (artwork != null) {
                    // TODO: UseCase를 통한 공개/비공개 전환
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
                            if (updatedArtwork.isPublished) "작품이 공개되었습니다" else "작품이 비공개로 전환되었습니다"
                        )
                    )
                }
            }

            is MyPageAction.SelectSortOption -> {
                reduce {
                    state.copy(sortOption = action.option)
                }
                // 정렬된 작품 목록 업데이트
                applySortAndFilter()
            }

            is MyPageAction.SelectFilterOption -> {
                reduce {
                    state.copy(filterOption = action.option)
                }
                // 필터링된 작품 목록 업데이트
                applySortAndFilter()
            }

            is MyPageAction.RefreshMyArtworks -> {
                reduce {
                    state.copy(isLoading = true)
                }

                // TODO: UseCase를 통한 새로고침
                // val artworks = getMyArtworksUseCase()

                reduce {
                    state.copy(
                        isLoading = false,
                        myArtworks = emptyList() // TODO: 실제 데이터로 교체
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

            // 인벤토리 관련 액션은 InventoryViewModel에서 처리
            is MyPageAction.SelectCategory,
            is MyPageAction.SelectItem,
            is MyPageAction.HideItemDetail,
            is MyPageAction.EquipItem,
            is MyPageAction.UnequipItem -> {
                // InventoryViewModel에서 처리
            }
        }
    }

    /**
     * 작품 ID로 작품 찾기
     */
    private fun findArtworkById(artworkId: String): Artwork? {
        val state = container.stateFlow.value
        return state.myArtworks.find { it.id == artworkId }
            ?: state.draftArtworks.find { it.id == artworkId }
            ?: state.publishedArtworks.find { it.id == artworkId }
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

    /**
     * 정렬 및 필터 적용
     */
    private fun applySortAndFilter() = intent {
        val state = container.stateFlow.value
        var filtered = state.myArtworks

        // 필터 적용
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
                // 필터 없음
            }
        }

        // 정렬 적용
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

