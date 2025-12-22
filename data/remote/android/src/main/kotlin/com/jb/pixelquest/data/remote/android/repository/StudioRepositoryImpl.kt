package com.jb.pixelquest.data.remote.android.repository

import com.jb.pixelquest.data.local.android.datasource.CanvasLocalDataSource
import com.jb.pixelquest.shared.data.remote.datasource.StudioRemoteDataSource
import com.jb.pixelquest.shared.domain.model.studio.Brush
import com.jb.pixelquest.shared.domain.model.studio.Canvas
import com.jb.pixelquest.shared.domain.model.studio.Palette
import com.jb.pixelquest.shared.domain.repository.studio.StudioRepository
import javax.inject.Inject

/**
 * 스튜디오 Repository 구현체
 * Remote와 Local DataSource를 조합하여 구현
 */
class StudioRepositoryImpl @Inject constructor(
    private val remoteDataSource: StudioRemoteDataSource,
    private val localDataSource: CanvasLocalDataSource
) : StudioRepository {
    
    override suspend fun getCanvases(): Result<List<Canvas>> {
        return remoteDataSource.getCanvases()
            .onSuccess { canvases ->
                localDataSource.saveCanvases(canvases)
            }
            .onFailure {
                val localCanvases = localDataSource.getAllCanvases()
                if (localCanvases.isNotEmpty()) {
                    return Result.success(localCanvases)
                }
            }
    }

    override suspend fun getCanvasById(id: String): Result<Canvas> {
        return remoteDataSource.getCanvasById(id)
            .onSuccess { canvas ->
                localDataSource.saveCanvas(canvas)
            }
            .onFailure {
                val localCanvas = localDataSource.getCanvasById(id)
                if (localCanvas != null) {
                    return Result.success(localCanvas)
                }
            }
    }

    override suspend fun createCanvas(canvas: Canvas): Result<Canvas> {
        return remoteDataSource.createCanvas(canvas)
            .onSuccess { createdCanvas ->
                localDataSource.saveCanvas(createdCanvas)
            }
    }

    override suspend fun updateCanvas(canvas: Canvas): Result<Canvas> {
        return remoteDataSource.updateCanvas(canvas)
            .onSuccess { updatedCanvas ->
                localDataSource.updateCanvas(updatedCanvas)
            }
    }

    override suspend fun deleteCanvas(id: String): Result<Unit> {
        return remoteDataSource.deleteCanvas(id)
            .onSuccess {
                localDataSource.deleteCanvas(id)
            }
    }

    override suspend fun getPalettes(): Result<List<Palette>> {
        return remoteDataSource.getPalettes()
    }

    override suspend fun getPaletteById(id: String): Result<Palette> {
        return remoteDataSource.getPaletteById(id)
    }

    override suspend fun getBrushes(): Result<List<Brush>> {
        return remoteDataSource.getBrushes()
    }

    override suspend fun getBrushById(id: String): Result<Brush> {
        return remoteDataSource.getBrushById(id)
    }
}

