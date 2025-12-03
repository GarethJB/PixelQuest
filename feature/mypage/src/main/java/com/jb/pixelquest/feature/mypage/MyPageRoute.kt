package com.jb.pixelquest.feature.mypage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jb.pixelquest.feature.mypage.model.MyPageAction
import com.jb.pixelquest.feature.mypage.ui.screen.MyPageScreen
import com.jb.pixelquest.feature.mypage.viewmodel.InventoryViewModel
import com.jb.pixelquest.feature.mypage.viewmodel.MyPageViewModel

/**
 * MyPage Route
 * State Hoisting: 상태는 ViewModel에서 관리하고, Screen에 전달
 */
@Composable
fun MyPageRoute(
    myPageViewModel: MyPageViewModel = viewModel(),
    inventoryViewModel: InventoryViewModel = viewModel()
) {
    val uiState by myPageViewModel.container.stateFlow.collectAsStateWithLifecycle()
    val inventoryState by inventoryViewModel.container.stateFlow.collectAsStateWithLifecycle()

    // 액션을 적절한 ViewModel로 라우팅
    fun handleAction(action: MyPageAction) {
        when (action) {
            is MyPageAction.SelectCategory,
            is MyPageAction.SelectItem,
            is MyPageAction.HideItemDetail,
            is MyPageAction.EquipItem,
            is MyPageAction.UnequipItem -> {
                inventoryViewModel.handleAction(action)
            }
            else -> {
                myPageViewModel.handleAction(action)
            }
        }
    }

    MyPageScreen(
        uiState = uiState,
        inventoryState = inventoryState,
        onAction = ::handleAction
    )
}
