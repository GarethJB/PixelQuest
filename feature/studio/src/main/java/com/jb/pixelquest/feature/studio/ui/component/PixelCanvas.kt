package com.jb.pixelquest.feature.studio.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntSize

/**
 * 픽셀 캔버스 컴포넌트
 * State Hoisting: 픽셀 변경은 이벤트만 위로 전달
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
            .pointerInput(canvasSize, zoom, panOffset, selectedColor) {
                // 포인터 이벤트 직접 처리 (터치 다운 시 즉시 반응, 드래그 중 연속 그리기)
                awaitPointerEventScope {
                    var lastPixelPos: PixelPosition? = null
                    var isDrawing = false
                    
                    while (true) {
                        val event = awaitPointerEvent(pass = PointerEventPass.Initial)
                        val change = event.changes.first()
                        val position = change.position
                        
                        when {
                            // 1. 포인터 다운 이벤트 - 즉시 색 채우기
                            change.pressed && !isDrawing -> {
                                val pixelPos = screenToPixel(
                                    tapOffset = position,
                                    canvasSize = canvasSize,
                                    zoom = zoom,
                                    panOffset = panOffset,
                                    canvasArea = size
                                )
                                pixelPos?.let {
                                    onPixelChanged(it.x, it.y, selectedColor)
                                    lastPixelPos = it
                                    isDrawing = true
                                }
                            }
                            
                            // 2. 드래그 중 - 연속 그리기 (pressed 상태이고 위치가 변경됨)
                            change.pressed && isDrawing -> {
                                val currentPixelPos = screenToPixel(
                                    tapOffset = position,
                                    canvasSize = canvasSize,
                                    zoom = zoom,
                                    panOffset = panOffset,
                                    canvasArea = size
                                )
                                
                                currentPixelPos?.let { current ->
                                    lastPixelPos?.let { last ->
                                        if (last != current) {
                                            // 두 점 사이의 모든 픽셀을 채움 (Bresenham 알고리즘)
                                            drawLineBetweenPixels(last, current) { x, y ->
                                                onPixelChanged(x, y, selectedColor)
                                            }
                                        }
                                    }
                                    lastPixelPos = current
                                }
                            }
                            
                            // 3. 포인터 업 이벤트 - 상태 초기화
                            !change.pressed && isDrawing -> {
                                lastPixelPos = null
                                isDrawing = false
                            }
                        }
                    }
                }
            }
            .pointerInput(zoom, panOffset) {
                // 줌/팬 제스처 (두 손가락)
                detectTransformGestures { _, pan, zoomChange, _ ->
                    val newZoom = (zoom * zoomChange).coerceIn(0.5f, 5f)
                    val newPan = panOffset + pan
                    onZoomChange(newZoom)
                    onPanChange(newPan)
                }
            }
    ) {
        // 배경
        drawRect(Color(0xFFF5F5F5))
        
        // 픽셀 그리기
        drawPixels(
            pixels = pixels,
            canvasSize = canvasSize,
            zoom = zoom,
            panOffset = panOffset
        )
        
        // 그리드 표시
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
 * 픽셀 그리기
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
 * 그리드 그리기
 */
private fun DrawScope.drawGrid(
    canvasSize: IntSize,
    zoom: Float,
    panOffset: Offset
) {
    val pixelWidth = size.width / canvasSize.width * zoom
    val pixelHeight = size.height / canvasSize.height * zoom
    
    // 세로선
    for (x in 0..canvasSize.width) {
        val lineX = x * pixelWidth + panOffset.x
        drawLine(
            color = Color.Gray.copy(alpha = 0.3f),
            start = Offset(lineX, panOffset.y),
            end = Offset(lineX, canvasSize.height * pixelHeight + panOffset.y),
            strokeWidth = 1f
        )
    }
    
    // 가로선
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
 * 화면 좌표를 픽셀 좌표로 변환
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

/**
 * 두 픽셀 위치 사이의 선을 그리기 (Bresenham 알고리즘)
 */
private fun drawLineBetweenPixels(
    start: PixelPosition,
    end: PixelPosition,
    onPixel: (Int, Int) -> Unit
) {
    var x0 = start.x
    var y0 = start.y
    val x1 = end.x
    val y1 = end.y
    
    val dx = kotlin.math.abs(x1 - x0)
    val dy = kotlin.math.abs(y1 - y0)
    val sx = if (x0 < x1) 1 else -1
    val sy = if (y0 < y1) 1 else -1
    var err = dx - dy
    
    while (true) {
        onPixel(x0, y0)
        
        if (x0 == x1 && y0 == y1) break
        
        val e2 = 2 * err
        if (e2 > -dy) {
            err -= dy
            x0 += sx
        }
        if (e2 < dx) {
            err += dx
            y0 += sy
        }
    }
}
