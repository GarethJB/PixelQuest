package com.jb.pixelquest.feature.mypage.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jb.pixelquest.feature.mypage.model.InventoryState
import com.jb.pixelquest.feature.mypage.model.MyPageAction
import com.jb.pixelquest.feature.mypage.model.MyPageTab
import com.jb.pixelquest.feature.mypage.model.MyPageUiState
import com.jb.pixelquest.feature.mypage.ui.component.MyPageTabRow
import com.jb.pixelquest.presentation.component.ScreenHeader
import com.jb.pixelquest.shared.presentation.resources.R

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
            MyPageTabRow(
                selectedTab = uiState.selectedTab,
                onTabSelected = { tab ->
                    onAction(MyPageAction.SelectTab(tab))
                }
            )

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

