package com.jb.pixelquest.feature.studio.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jb.pixelquest.feature.studio.model.Brush
import com.jb.pixelquest.shared.presentation.resources.R

/**
 * 브러??리스??컴포?�트
 * State Hoisting: ?�택 ?�벤?�만 ?�위�??�달
 */
@Composable
fun BrushList(
    brushes: List<Brush>,
    onBrushSelected: (Brush) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.brushes),
            style = MaterialTheme.typography.titleMedium
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(
                items = brushes,
                key = { it.id }
            ) { brush ->
                BrushCard(
                    brush = brush,
                    onClick = { onBrushSelected(brush) }
                )
            }
        }
    }
}

@Composable
private fun BrushCard(
    brush: Brush,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.width(100.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 브러??미리보기
            brush.previewImagePath?.let { imagePath ->
                AsyncImage(
                    model = imagePath,
                    contentDescription = brush.name,
                    modifier = Modifier.size(48.dp)
                )
            } ?: run {
                // 기본 브러???�이�?
                Box(
                    modifier = Modifier.size(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "??",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }

            Text(
                text = brush.name,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Size: ${brush.size}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

