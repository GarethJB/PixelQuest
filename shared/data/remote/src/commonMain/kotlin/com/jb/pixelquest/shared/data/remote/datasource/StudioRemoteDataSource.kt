package com.jb.pixelquest.shared.data.remote.datasource

import com.jb.pixelquest.shared.domain.model.studio.Brush
import com.jb.pixelquest.shared.domain.model.studio.Canvas
import com.jb.pixelquest.shared.domain.model.studio.Palette

/**
 * 스튜디오 Remote DataSource 인터페이스
 */
interface StudioRemoteDataSource {
    suspend fun getCanvases(): Result<List<Canvas>>
    suspend fun getCanvasById(id: String): Result<Canvas>
    suspend fun createCanvas(canvas: Canvas): Result<Canvas>
    suspend fun updateCanvas(canvas: Canvas): Result<Canvas>
    suspend fun deleteCanvas(id: String): Result<Unit>
    suspend fun getPalettes(): Result<List<Palette>>
    suspend fun getPaletteById(id: String): Result<Palette>
    suspend fun getBrushes(): Result<List<Brush>>
    suspend fun getBrushById(id: String): Result<Brush>
}
