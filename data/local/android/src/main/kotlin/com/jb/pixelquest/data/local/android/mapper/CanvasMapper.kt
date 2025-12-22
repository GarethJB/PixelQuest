package com.jb.pixelquest.data.local.android.mapper

import com.jb.pixelquest.data.local.android.entity.CanvasEntity
import com.jb.pixelquest.shared.domain.model.common.CanvasSize
import com.jb.pixelquest.shared.domain.model.studio.Canvas

/**
 * Canvas Entity ↔ Domain 모델 Mapper
 */
object CanvasMapper {
    fun CanvasEntity.toDomain(): Canvas {
        return Canvas(
            id = id,
            name = name,
            thumbnailPath = thumbnailPath,
            lastModified = lastModified,
            canvasSize = CanvasSize(canvasWidth, canvasHeight),
            filePath = filePath
        )
    }

    fun Canvas.toEntity(): CanvasEntity {
        return CanvasEntity(
            id = id,
            name = name,
            thumbnailPath = thumbnailPath,
            lastModified = lastModified,
            canvasWidth = canvasSize.width,
            canvasHeight = canvasSize.height,
            filePath = filePath
        )
    }
}

