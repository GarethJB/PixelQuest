package com.jb.pixelquest.feature.mypage.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment.Companion.Center
import com.jb.pixelquest.feature.mypage.model.InventoryState
import com.jb.pixelquest.feature.mypage.model.MyPageAction
import com.jb.pixelquest.feature.mypage.model.MyPageTab
import com.jb.pixelquest.feature.mypage.model.MyPageUiState
import com.jb.pixelquest.feature.mypage.ui.component.MyPageTabRow
import com.jb.pixelquest.feature.mypage.ui.screen.InventoryScreen
import com.jb.pixelquest.feature.mypage.ui.screen.MyArtworksScreen
import com.jb.pixelquest.presentation.component.ScreenHeader
import com.jb.pixelquest.presentation.resources.R

/**
 * MyPage 메인 화면
 * State Hoisting 패턴: 상태는 상위에서 관리하고, 액션만 전달받음
 */
@Composable
fun MyPageScreen(
    uiState: MyPageUiState,
    inventoryState: InventoryState,
    onAction: (MyPageAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            ScreenHeader(titleResId = R.string.mypage_title)
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // 탭 행
            MyPageTabRow(
                selectedTab = uiState.selectedTab,
                onTabSelected = { tab ->
                    onAction(MyPageAction.SelectTab(tab))
                }
            )

            // 탭별 콘텐츠
            when (uiState.selectedTab) {
                MyPageTab.MY_ARTWORKS -> {
                    MyArtworksScreen(
                        uiState = uiState,
                        onAction = onAction
                    )
                }
                MyPageTab.INVENTORY -> {
                    InventoryScreen(
                        inventoryState = inventoryState,
                        onAction = onAction
                    )
                }
            }
        }
    }
}

