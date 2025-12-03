package com.jb.pixelquest.presentation.component

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHeader(
    titleResId: Int,
    modifier: Modifier = Modifier
) {
    MediumTopAppBar(
        title = {
            Text(
                text = stringResource(id = titleResId),
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(),
        windowInsets = WindowInsets(0),
        modifier = modifier
    )
}

