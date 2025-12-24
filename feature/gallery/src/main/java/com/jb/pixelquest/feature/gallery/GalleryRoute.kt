package com.jb.pixelquest.feature.gallery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jb.pixelquest.feature.gallery.ui.screen.GalleryScreen
import com.jb.pixelquest.feature.gallery.viewmodel.GalleryViewModel

@Composable
fun GalleryRoute(
    viewModel: GalleryViewModel = hiltViewModel()
) {
    val uiState by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    GalleryScreen(
        uiState = uiState,
        onAction = viewModel::handleAction
    )
}
