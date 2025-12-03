package com.jb.pixelquest.feature.mypage.viewmodel

import androidx.lifecycle.ViewModel
import com.jb.pixelquest.feature.mypage.model.InventoryItem
import com.jb.pixelquest.feature.mypage.model.InventoryItemType
import com.jb.pixelquest.feature.mypage.model.InventoryState
import com.jb.pixelquest.feature.mypage.model.MyPageAction
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 * 인벤토리 ViewModel
 * Orbit MVI 패턴 사용
 */
@HiltViewModel
class InventoryViewModel @Inject constructor(
    // TODO: UseCase 주입
    // private val getInventoryItemsUseCase: GetInventoryItemsUseCase,
    // private val equipItemUseCase: EquipItemUseCase,
    // private val unequipItemUseCase: UnequipItemUseCase,
) : ContainerHost<InventoryState, Nothing>, ViewModel() {

    override val container = container<InventoryState, Nothing>(
        InventoryState()
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
        // val palettes = getInventoryItemsUseCase(InventoryCategory.PALETTE)
        // val brushes = getInventoryItemsUseCase(InventoryCategory.BRUSH)
        // val badges = getInventoryItemsUseCase(InventoryCategory.BADGE)
        // val decorations = getInventoryItemsUseCase(InventoryCategory.PROFILE_DECORATION)

        // 임시 데이터 (개발용)
        val mockPalettes = emptyList<InventoryItem>()
        val mockBrushes = emptyList<InventoryItem>()
        val mockBadges = emptyList<InventoryItem>()
        val mockDecorations = emptyList<InventoryItem>()

        reduce {
            state.copy(
                isLoading = false,
                palettes = mockPalettes,
                brushes = mockBrushes,
                badges = mockBadges,
                profileDecorations = mockDecorations
            )
        }
    }

    /**
     * 액션 처리
     */
    fun handleAction(action: MyPageAction) = intent {
        when (action) {
            is MyPageAction.SelectCategory -> {
                reduce {
                    state.copy(selectedCategory = action.category)
                }
            }

            is MyPageAction.SelectItem -> {
                reduce {
                    state.copy(
                        selectedItem = action.item,
                        showItemDetail = true
                    )
                }
            }

            is MyPageAction.HideItemDetail -> {
                reduce {
                    state.copy(
                        showItemDetail = false,
                        selectedItem = null
                    )
                }
            }

            is MyPageAction.EquipItem -> {
                val item = findItemById(action.itemId)
                if (item != null && !item.isEquipped) {
                    // TODO: UseCase를 통한 아이템 장착
                    // equipItemUseCase(action.itemId)

                    // 같은 타입의 다른 아이템 해제
                    val updatedItems = getItemsByCategory(item.type).map { i ->
                        if (i.id == action.itemId) {
                            i.copy(isEquipped = true)
                        } else if (i.isEquipped && i.type == item.type) {
                            i.copy(isEquipped = false)
                        } else {
                            i
                        }
                    }

                    updateItemsByCategory(item.type, updatedItems)

                    reduce {
                        state.copy(
                            selectedItem = item.copy(isEquipped = true)
                        )
                    }
                }
            }

            is MyPageAction.UnequipItem -> {
                val item = findItemById(action.itemId)
                if (item != null && item.isEquipped) {
                    // TODO: UseCase를 통한 아이템 해제
                    // unequipItemUseCase(action.itemId)

                    val updatedItems = getItemsByCategory(item.type).map { i ->
                        if (i.id == action.itemId) {
                            i.copy(isEquipped = false)
                        } else {
                            i
                        }
                    }

                    updateItemsByCategory(item.type, updatedItems)

                    reduce {
                        state.copy(
                            selectedItem = item.copy(isEquipped = false)
                        )
                    }
                }
            }

            else -> {
                // 다른 액션은 무시
            }
        }
    }

    /**
     * 아이템 ID로 아이템 찾기
     */
    private fun findItemById(itemId: String): InventoryItem? {
        val state = container.stateFlow.value
        return state.palettes.find { it.id == itemId }
            ?: state.brushes.find { it.id == itemId }
            ?: state.badges.find { it.id == itemId }
            ?: state.profileDecorations.find { it.id == itemId }
            ?: state.selectedItem?.takeIf { it.id == itemId }
    }

    /**
     * 카테고리별 아이템 가져오기
     */
    private fun getItemsByCategory(type: InventoryItemType): List<InventoryItem> {
        val state = container.stateFlow.value
        return when (type) {
            InventoryItemType.PALETTE -> state.palettes
            InventoryItemType.BRUSH -> state.brushes
            InventoryItemType.BADGE -> state.badges
            InventoryItemType.PROFILE_FRAME,
            InventoryItemType.PROFILE_BACKGROUND,
            InventoryItemType.PROFILE_ICON -> state.profileDecorations
        }
    }

    /**
     * 카테고리별 아이템 업데이트
     */
    private fun updateItemsByCategory(
        type: InventoryItemType,
        updatedItems: List<InventoryItem>
    ) = intent {
        when (type) {
            InventoryItemType.PALETTE -> {
                reduce {
                    state.copy(palettes = updatedItems)
                }
            }
            InventoryItemType.BRUSH -> {
                reduce {
                    state.copy(brushes = updatedItems)
                }
            }
            InventoryItemType.BADGE -> {
                reduce {
                    state.copy(badges = updatedItems)
                }
            }
            InventoryItemType.PROFILE_FRAME,
            InventoryItemType.PROFILE_BACKGROUND,
            InventoryItemType.PROFILE_ICON -> {
                reduce {
                    state.copy(profileDecorations = updatedItems)
                }
            }
        }
    }
}

