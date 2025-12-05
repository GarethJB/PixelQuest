package com.jb.pixelquest.feature.studio.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.shared.presentation.resources.R

/**
 * ??ìº”ë²„??ë§Œë“¤ê¸?ë²„íŠ¼
 * State Hoisting: ?´ë¦­ ?´ë²¤?¸ë§Œ ?ìœ„ë¡??„ë‹¬
 */
@Composable
fun NewCanvasButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        colors = ButtonDefaults.filledTonalButtonColors()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.create_new_canvas),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

