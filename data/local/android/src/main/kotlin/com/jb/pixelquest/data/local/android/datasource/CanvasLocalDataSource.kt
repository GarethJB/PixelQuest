package com.jb.pixelquest.data.local.android.datasource

import com.jb.pixelquest.data.local.android.dao.CanvasDao
import com.jb.pixelquest.data.local.android.mapper.CanvasMapper.toDomain
import com.jb.pixelquest.data.local.android.mapper.CanvasMapper.toEntity
import com.jb.pixelquest.shared.domain.model.studio.Canvas
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * 캔버스 Local DataSource 구현체
 */
class CanvasLocalDataSource(
    private val canvasDao: CanvasDao
) {
    suspend fun getCanvasById(id: String): Canvas? {
        return canvasDao.getCanvasById(id)?.toDomain()
    }

    suspend fun getAllCanvases(): List<Canvas> {
        return canvasDao.getAllCanvases()
            .map { it.toDomain() }
    }

    fun observeAllCanvases(): Flow<List<Canvas>> {
        return canvasDao.observeAllCanvases()
            .map { entities -> entities.map { it.toDomain() } }
    }

    suspend fun saveCanvas(canvas: Canvas) {
        canvasDao.insertCanvas(canvas.toEntity())
    }

    suspend fun saveCanvases(canvases: List<Canvas>) {
        canvasDao.insertCanvases(canvases.map { it.toEntity() })
    }

    suspend fun updateCanvas(canvas: Canvas) {
        canvasDao.updateCanvas(canvas.toEntity())
    }

    suspend fun deleteCanvas(id: String) {
        canvasDao.deleteCanvas(id)
    }

    suspend fun deleteAllCanvases() {
        canvasDao.deleteAllCanvases()
    }
}

