package com.jb.pixelquest.feature.mypage.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment.Companion.Center
import com.jb.pixelquest.feature.mypage.model.InventoryCategory
import com.jb.pixelquest.feature.mypage.model.InventoryState
import com.jb.pixelquest.feature.mypage.model.MyPageAction
import com.jb.pixelquest.feature.mypage.ui.component.InventoryCategoryTabs
import com.jb.pixelquest.feature.mypage.ui.component.InventoryItemGrid
import com.jb.pixelquest.feature.mypage.ui.screen.InventoryItemDetailScreen

/**
 * 인벤토리 화면
 * State Hoisting 패턴: 상태는 상위에서 관리하고, 액션만 전달받음
 */
@Composable
fun InventoryScreen(
    inventoryState: InventoryState,
    onAction: (MyPageAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // 카테고리 탭
        InventoryCategoryTabs(
            selectedCategory = inventoryState.selectedCategory,
            onCategorySelected = { category ->
                onAction(MyPageAction.SelectCategory(category))
            }
        )

        // 아이템 그리드
        Box(modifier = Modifier.fillMaxSize()) {
            val items = when (inventoryState.selectedCategory) {
                InventoryCategory.PALETTE -> inventoryState.palettes
                InventoryCategory.BRUSH -> inventoryState.brushes
                InventoryCategory.BADGE -> inventoryState.badges
                InventoryCategory.PROFILE_DECORATION -> inventoryState.profileDecorations
                null -> emptyList()
            }

            InventoryItemGrid(
                items = items,
                onItemSelected = { item ->
                    onAction(MyPageAction.SelectItem(item))
                }
            )

            // 로딩 인디케이터
            if (inventoryState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Center)
                )
            }
        }
    }

    // 아이템 상세 다이얼로그
    if (inventoryState.showItemDetail && inventoryState.selectedItem != null) {
        InventoryItemDetailScreen(
            item = inventoryState.selectedItem,
            onEquipClick = {
                if (inventoryState.selectedItem.isEquipped) {
                    onAction(MyPageAction.UnequipItem(inventoryState.selectedItem.id))
                } else {
                    onAction(MyPageAction.EquipItem(inventoryState.selectedItem.id))
                }
            },
            onDismiss = {
                onAction(MyPageAction.HideItemDetail)
            }
        )
    }
}

