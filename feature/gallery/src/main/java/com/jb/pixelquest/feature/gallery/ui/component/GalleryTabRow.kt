package com.jb.pixelquest.feature.gallery.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.feature.gallery.model.GalleryTab
import com.jb.pixelquest.shared.presentation.resources.R

@Composable
fun GalleryTabRow(
    selectedTab: GalleryTab,
    onTabSelected: (GalleryTab) -> Unit,
    modifier: Modifier = Modifier
) {
    ScrollableTabRow(
        selectedTabIndex = GalleryTab.values().indexOf(selectedTab),
        modifier = modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        edgePadding = 16.dp
    ) {
        GalleryTab.values().forEach { tab ->
            Tab(
                selected = selectedTab == tab,
                onClick = { onTabSelected(tab) },
                text = {
                    Text(
                        text = when (tab) {
                            GalleryTab.TRENDING -> stringResource(id = R.string.trending)
                            GalleryTab.LATEST -> stringResource(id = R.string.latest)
                            GalleryTab.CATEGORY -> stringResource(id = R.string.category)
                        }
                    )
                }
            )
        }
    }
}

