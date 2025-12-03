package com.jb.pixelquest.feature.gallery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.presentation.component.ScreenHeader
import com.jb.pixelquest.presentation.resources.R

@Composable
fun GalleryRoute() {
    GalleryScreen()
}

@Composable
fun GalleryScreen() {
    Scaffold(
        topBar = {
            ScreenHeader(titleResId = R.string.gallery_title)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.gallery_screen),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}


