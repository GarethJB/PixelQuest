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
import com.jb.pixelquest.shared.presentation.resources.R

/**
 * MyPage ë©”ì¸ ?”ë©´
 * State Hoisting ?¨í„´: ?íƒœ???ìœ„?ì„œ ê´€ë¦¬í•˜ê³? ?¡ì…˜ë§??„ë‹¬ë°›ìŒ
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
            // ????
            MyPageTabRow(
                selectedTab = uiState.selectedTab,
                onTabSelected = { tab ->
                    onAction(MyPageAction.SelectTab(tab))
                }
            )

            // ??³„ ì½˜í…ì¸?
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

