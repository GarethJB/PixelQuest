package com.jb.pixelquest.feature.studio.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntSize

/**
 * ?ΩÏ? Ï∫îÎ≤Ñ??Ïª¥Ìè¨?åÌä∏
 * State Hoisting: ?ΩÏ? Î≥ÄÍ≤? Ï§???Î≥ÄÍ≤??¥Î≤§?∏Îßå ?ÅÏúÑÎ°??ÑÎã¨
 */
@Composable
fun PixelCanvas(
    canvasSize: IntSize,
    pixels: Array<Array<Color>>,
    selectedColor: Color,
    showGrid: Boolean,
    zoom: Float,
    panOffset: Offset,
    onPixelChanged: (Int, Int, Color) -> Unit,
    onZoomChange: (Float) -> Unit,
    onPanChange: (Offset) -> Unit,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                // ???úÏä§Ï≤?(?®Ïùº ?ΩÏ? Í∑∏Î¶¨Í∏?
                detectTapGestures { tapOffset ->
                    val pixelPos = screenToPixel(
                        tapOffset = tapOffset,
                        canvasSize = canvasSize,
                        zoom = zoom,
                        panOffset = panOffset,
                        canvasArea = size
                    )
                    
                    pixelPos?.let {
                        onPixelChanged(it.x, it.y, selectedColor)
                    }
                }
            }
            .pointerInput(Unit) {
                // ?úÎûòÍ∑??úÏä§Ï≤?(?∞ÏÜç Í∑∏Î¶¨Í∏?
                detectDragGestures(
                    onDragStart = { offset ->
                        val pixelPos = screenToPixel(
                            tapOffset = offset,
                            canvasSize = canvasSize,
                            zoom = zoom,
                            panOffset = panOffset,
                            canvasArea = size
                        )
                        pixelPos?.let {
                            onPixelChanged(it.x, it.y, selectedColor)
                        }
                    }
                ) { change, _ ->
                    val pixelPos = screenToPixel(
                        tapOffset = change.position,
                        canvasSize = canvasSize,
                        zoom = zoom,
                        panOffset = panOffset,
                        canvasArea = size
                    )
                    
                    pixelPos?.let {
                        onPixelChanged(it.x, it.y, selectedColor)
                    }
                }
            }
            .pointerInput(zoom, panOffset) {
                // Ï§????úÏä§Ï≤?
                detectTransformGestures { _, pan, zoomChange, _ ->
                    val newZoom = (zoom * zoomChange).coerceIn(0.5f, 5f)
                    val newPan = panOffset + pan
                    onZoomChange(newZoom)
                    onPanChange(newPan)
                }
            }
    ) {
        // Î∞∞Í≤Ω
        drawRect(Color(0xFFF5F5F5))
        
        // ?ΩÏ? Í∑∏Î¶¨Í∏?
        drawPixels(
            pixels = pixels,
            canvasSize = canvasSize,
            zoom = zoom,
            panOffset = panOffset
        )
        
        // Í∑∏Î¶¨???úÏãú
        if (showGrid) {
            drawGrid(
                canvasSize = canvasSize,
                zoom = zoom,
                panOffset = panOffset
            )
        }
    }
}

/**
 * ?ΩÏ? Í∑∏Î¶¨Í∏?
 */
private fun DrawScope.drawPixels(
    pixels: Array<Array<Color>>,
    canvasSize: IntSize,
    zoom: Float,
    panOffset: Offset
) {
    val pixelWidth = size.width / canvasSize.width * zoom
    val pixelHeight = size.height / canvasSize.height * zoom
    
    for (y in pixels.indices) {
        for (x in pixels[y].indices) {
            val pixelX = x * pixelWidth + panOffset.x
            val pixelY = y * pixelHeight + panOffset.y
            
            drawRect(
                color = pixels[y][x],
                topLeft = Offset(pixelX, pixelY),
                size = androidx.compose.ui.geometry.Size(pixelWidth, pixelHeight)
            )
        }
    }
}

/**
 * Í∑∏Î¶¨??Í∑∏Î¶¨Í∏?
 */
private fun DrawScope.drawGrid(
    canvasSize: IntSize,
    zoom: Float,
    panOffset: Offset
) {
    val pixelWidth = size.width / canvasSize.width * zoom
    val pixelHeight = size.height / canvasSize.height * zoom
    
    // ?∏Î°ú??
    for (x in 0..canvasSize.width) {
        val lineX = x * pixelWidth + panOffset.x
        drawLine(
            color = Color.Gray.copy(alpha = 0.3f),
            start = Offset(lineX, panOffset.y),
            end = Offset(lineX, canvasSize.height * pixelHeight + panOffset.y),
            strokeWidth = 1f
        )
    }
    
    // Í∞ÄÎ°úÏÑ†
    for (y in 0..canvasSize.height) {
        val lineY = y * pixelHeight + panOffset.y
        drawLine(
            color = Color.Gray.copy(alpha = 0.3f),
            start = Offset(panOffset.x, lineY),
            end = Offset(canvasSize.width * pixelWidth + panOffset.x, lineY),
            strokeWidth = 1f
        )
    }
}

/**
 * ?îÎ©¥ Ï¢åÌëúÎ•??ΩÏ? Ï¢åÌëúÎ°?Î≥Ä??
 */
private fun screenToPixel(
    tapOffset: Offset,
    canvasSize: IntSize,
    zoom: Float,
    panOffset: Offset,
    canvasArea: IntSize
): PixelPosition? {
    val pixelWidth = canvasArea.width.toFloat() / canvasSize.width * zoom
    val pixelHeight = canvasArea.height.toFloat() / canvasSize.height * zoom
    
    val adjustedX = tapOffset.x - panOffset.x
    val adjustedY = tapOffset.y - panOffset.y
    
    val pixelX = (adjustedX / pixelWidth).toInt()
    val pixelY = (adjustedY / pixelHeight).toInt()
    
    return if (pixelX in 0 until canvasSize.width && 
               pixelY in 0 until canvasSize.height) {
        PixelPosition(pixelX, pixelY)
    } else {
        null
    }
}

private data class PixelPosition(val x: Int, val y: Int)

