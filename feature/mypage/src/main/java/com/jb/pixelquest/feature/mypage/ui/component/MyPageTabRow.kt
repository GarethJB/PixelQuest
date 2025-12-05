package com.jb.pixelquest.feature.mypage.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.feature.mypage.model.MyPageTab
import com.jb.pixelquest.shared.presentation.resources.R

/**
 * MyPage ????
 * State Hoisting: ? íƒ ?´ë²¤?¸ë§Œ ?ìœ„ë¡??„ë‹¬
 */
@Composable
fun MyPageTabRow(
    selectedTab: MyPageTab,
    onTabSelected: (MyPageTab) -> Unit,
    modifier: Modifier = Modifier
) {
    ScrollableTabRow(
        selectedTabIndex = MyPageTab.values().indexOf(selectedTab),
        modifier = modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        edgePadding = 16.dp
    ) {
        MyPageTab.values().forEach { tab ->
            Tab(
                selected = selectedTab == tab,
                onClick = { onTabSelected(tab) },
                text = {
                    Text(
                        text = when (tab) {
                            MyPageTab.MY_ARTWORKS -> stringResource(id = R.string.my_artworks)
                            MyPageTab.INVENTORY -> stringResource(id = R.string.inventory)
                        }
                    )
                }
            )
        }
    }
}

