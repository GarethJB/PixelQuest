package com.jb.pixelquest.shared.data.remote.datasource

import com.jb.pixelquest.shared.data.remote.api.PixelQuestApi
import com.jb.pixelquest.shared.data.remote.dto.CanvasDto
import com.jb.pixelquest.shared.data.remote.mapper.StudioMapper.toDomain
import com.jb.pixelquest.shared.data.remote.mapper.StudioMapper.toDto
import com.jb.pixelquest.shared.domain.model.studio.Brush
import com.jb.pixelquest.shared.domain.model.studio.Canvas
import com.jb.pixelquest.shared.domain.model.studio.Palette

/**
 * 스튜디오 Remote DataSource
 */
class StudioRemoteDataSource(
    private val api: PixelQuestApi
) {
    suspend fun getCanvases(): Result<List<Canvas>> {
        return try {
            val response = api.getCanvases()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getCanvasById(id: String): Result<Canvas> {
        return try {
            val response = api.getCanvasById(id)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun createCanvas(canvas: Canvas): Result<Canvas> {
        return try {
            val dto = canvas.toDto()
            val response = api.createCanvas(dto)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateCanvas(canvas: Canvas): Result<Canvas> {
        return try {
            val dto = canvas.toDto()
            val response = api.updateCanvas(canvas.id, dto)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun deleteCanvas(id: String): Result<Unit> {
        return try {
            api.deleteCanvas(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getPalettes(): Result<List<Palette>> {
        return try {
            val response = api.getPalettes()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getPaletteById(id: String): Result<Palette> {
        return try {
            val response = api.getPaletteById(id)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getBrushes(): Result<List<Brush>> {
        return try {
            val response = api.getBrushes()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getBrushById(id: String): Result<Brush> {
        return try {
            val response = api.getBrushById(id)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

