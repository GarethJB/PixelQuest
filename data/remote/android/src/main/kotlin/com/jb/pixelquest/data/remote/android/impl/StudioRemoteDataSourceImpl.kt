package com.jb.pixelquest.data.remote.android.impl

import com.jb.pixelquest.data.remote.android.api.PixelQuestApi
import com.jb.pixelquest.shared.data.remote.datasource.StudioRemoteDataSource
import com.jb.pixelquest.shared.data.remote.mapper.StudioMapper.toDomain
import com.jb.pixelquest.shared.data.remote.mapper.StudioMapper.toDto
import com.jb.pixelquest.shared.domain.model.studio.Brush
import com.jb.pixelquest.shared.domain.model.studio.Canvas
import com.jb.pixelquest.shared.domain.model.studio.Palette
import javax.inject.Inject

/**
 * 스튜디오 Remote DataSource 구현체
 */
class StudioRemoteDataSourceImpl @Inject constructor(
    private val api: PixelQuestApi
) : StudioRemoteDataSource {
    override suspend fun getCanvases(): Result<List<Canvas>> {
        return try {
            val response = api.getCanvases()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getCanvasById(id: String): Result<Canvas> {
        return try {
            val response = api.getCanvasById(id)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun createCanvas(canvas: Canvas): Result<Canvas> {
        return try {
            val dto = canvas.toDto()
            val response = api.createCanvas(dto)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun updateCanvas(canvas: Canvas): Result<Canvas> {
        return try {
            val dto = canvas.toDto()
            val response = api.updateCanvas(canvas.id, dto)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun deleteCanvas(id: String): Result<Unit> {
        return try {
            api.deleteCanvas(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getPalettes(): Result<List<Palette>> {
        return try {
            val response = api.getPalettes()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getPaletteById(id: String): Result<Palette> {
        return try {
            val response = api.getPaletteById(id)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getBrushes(): Result<List<Brush>> {
        return try {
            val response = api.getBrushes()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getBrushById(id: String): Result<Brush> {
        return try {
            val response = api.getBrushById(id)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

