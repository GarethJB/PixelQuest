package com.jb.pixelquest.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jb.pixelquest.feature.home.model.Canvas
import com.jb.pixelquest.shared.presentation.resources.R

/**
 * 이젤 뷰 (캔버스를 표시하는 이젤)
 */
@Composable
fun EaselView(
    canvas: Canvas,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val spacingSmall = dimensionResource(id = R.dimen.spacing_small)
    val spacingMedium = dimensionResource(id = R.dimen.spacing_medium)

    Card(
        modifier = modifier
            .size(width = 120.dp, height = 160.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacingSmall),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 이젤 프레임
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                // 캔버스 썸네일 또는 플레이스홀더
                if (canvas.thumbnailPath != null) {
                    AsyncImage(
                        model = canvas.thumbnailPath,
                        contentDescription = canvas.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    // 플레이스홀더: 캔버스 크기 표시
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${canvas.canvasSize.width}x${canvas.canvasSize.height}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // 캔버스 이름
            Text(
                text = canvas.name,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = spacingSmall),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

/**
 * 이젤 그리드 뷰 (여러 이젤을 표시)
 */
@Composable
fun EaselGridView(
    canvases: List<Canvas>,
    modifier: Modifier = Modifier,
    onCanvasClick: (Canvas) -> Unit = {}
) {
    if (canvases.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "생성된 캔버스가 없습니다",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    } else {
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.spacing_medium)
            ),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                horizontal = dimensionResource(id = R.dimen.spacing_medium)
            )
        ) {
            items(canvases) { canvas ->
                EaselView(
                    canvas = canvas,
                    onClick = { onCanvasClick(canvas) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EaselViewPreview() {
    EaselView(
        canvas = Canvas(
            id = "1",
            name = "My Canvas",
            lastModified = System.currentTimeMillis(),
            canvasSize = androidx.compose.ui.unit.IntSize(32, 32)
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun EaselGridViewPreview() {
    EaselGridView(
        canvases = listOf(
            Canvas(
                id = "1",
                name = "Canvas 1",
                lastModified = System.currentTimeMillis(),
                canvasSize = androidx.compose.ui.unit.IntSize(32, 32)
            ),
            Canvas(
                id = "2",
                name = "Canvas 2",
                lastModified = System.currentTimeMillis(),
                canvasSize = androidx.compose.ui.unit.IntSize(64, 64)
            ),
            Canvas(
                id = "3",
                name = "Canvas 3",
                lastModified = System.currentTimeMillis(),
                canvasSize = androidx.compose.ui.unit.IntSize(16, 16)
            )
        )
    )
}
