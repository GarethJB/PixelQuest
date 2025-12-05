package com.jb.pixelquest.feature.studio.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.feature.studio.model.Palette
import com.jb.pixelquest.shared.presentation.resources.R

/**
 * ?îÎ†à??Î¶¨Ïä§??Ïª¥Ìè¨?åÌä∏
 * State Hoisting: ?†ÌÉù ?¥Î≤§?∏Îßå ?ÅÏúÑÎ°??ÑÎã¨
 */
@Composable
fun PaletteList(
    palettes: List<Palette>,
    onPaletteSelected: (Palette) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.palettes),
            style = MaterialTheme.typography.titleMedium
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(
                items = palettes,
                key = { it.id }
            ) { palette ->
                PaletteCard(
                    palette = palette,
                    onClick = { onPaletteSelected(palette) }
                )
            }
        }
    }
}

@Composable
private fun PaletteCard(
    palette: Palette,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.width(120.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = palette.name,
                style = MaterialTheme.typography.titleSmall
            )
            
            // ?âÏÉÅ ÎØ∏Î¶¨Î≥¥Í∏∞
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                palette.colors.take(5).forEach { color ->
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(color, CircleShape)
                    )
                }
            }
        }
    }
}

