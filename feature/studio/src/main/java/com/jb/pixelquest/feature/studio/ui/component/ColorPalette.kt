package com.jb.pixelquest.feature.studio.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.feature.studio.model.Palette
import com.jb.pixelquest.shared.presentation.resources.R

/**
 * ?‰ìƒ ?”ë ˆ??ì»´í¬?ŒíŠ¸
 * State Hoisting: ?‰ìƒ ? íƒ ?´ë²¤?¸ë§Œ ?ìœ„ë¡??„ë‹¬
 */
@Composable
fun ColorPalette(
    selectedColor: Color,
    selectedPalette: Palette?,
    onColorSelected: (Color) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.color_palette),
            style = MaterialTheme.typography.titleSmall
        )

        // ê¸°ë³¸ ?‰ìƒ ?”ë ˆ??
        val defaultColors = listOf(
            Color.Black,
            Color.White,
            Color.Red,
            Color.Green,
            Color.Blue,
            Color.Yellow,
            Color.Cyan,
            Color.Magenta,
            Color(0xFFFF6B6B),
            Color(0xFF4ECDC4),
            Color(0xFF45B7D1),
            Color(0xFF96CEB4),
            Color(0xFFFECA57),
            Color(0xFFFF9FF3),
            Color(0xFF54A0FF),
            Color(0xFF5F27CD)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(defaultColors) { color ->
                ColorButton(
                    color = color,
                    isSelected = color == selectedColor,
                    onClick = { onColorSelected(color) }
                )
            }
        }

        // ? íƒ???”ë ˆ?¸ì˜ ?‰ìƒ??
        selectedPalette?.let { palette ->
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                items(palette.colors) { color ->
                    ColorButton(
                        color = color,
                        isSelected = color == selectedColor,
                        onClick = { onColorSelected(color) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ColorButton(
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.size(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            onClick = onClick,
            modifier = Modifier.size(36.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = color
            ),
            border = if (isSelected) {
                BorderStroke(3.dp, MaterialTheme.colorScheme.primary)
            } else {
                null
            }
        ) {}
    }
}

