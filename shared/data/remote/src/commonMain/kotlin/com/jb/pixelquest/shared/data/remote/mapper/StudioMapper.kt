package com.jb.pixelquest.shared.data.remote.mapper

import com.jb.pixelquest.shared.data.remote.dto.BrushDto
import com.jb.pixelquest.shared.data.remote.dto.CanvasDto
import com.jb.pixelquest.shared.data.remote.dto.PaletteDto
import com.jb.pixelquest.shared.domain.model.common.CanvasSize
import com.jb.pixelquest.shared.domain.model.common.Color
import com.jb.pixelquest.shared.domain.model.studio.Brush
import com.jb.pixelquest.shared.domain.model.studio.BrushShape
import com.jb.pixelquest.shared.domain.model.studio.Canvas
import com.jb.pixelquest.shared.domain.model.studio.Palette

/**
 * Studio 관련 DTO ↔ Domain 모델 Mapper
 */
object StudioMapper {
    
    fun CanvasDto.toDomain(): Canvas {
        return Canvas(
            id = id,
            name = name,
            thumbnailPath = thumbnailPath,
            lastModified = lastModified,
            canvasSize = CanvasSize(canvasWidth, canvasHeight),
            filePath = filePath
        )
    }
    
    fun Canvas.toDto(): CanvasDto {
        return CanvasDto(
            id = id,
            name = name,
            thumbnailPath = thumbnailPath,
            lastModified = lastModified,
            canvasWidth = canvasSize.width,
            canvasHeight = canvasSize.height,
            filePath = filePath
        )
    }
    
    fun PaletteDto.toDomain(): Palette {
        return Palette(
            id = id,
            name = name,
            colors = colors.map { Color.fromHex(it) },
            isDefault = isDefault
        )
    }
    
    fun Palette.toDto(): PaletteDto {
        return PaletteDto(
            id = id,
            name = name,
            colors = colors.map { it.toHexString() },
            isDefault = isDefault
        )
    }
    
    fun BrushDto.toDomain(): Brush {
        return Brush(
            id = id,
            name = name,
            size = size,
            shape = shape.toBrushShape(),
            previewImagePath = previewImagePath
        )
    }
    
    private fun String.toBrushShape(): BrushShape {
        return try {
            BrushShape.valueOf(this.uppercase())
        } catch (e: IllegalArgumentException) {
            BrushShape.CIRCLE
        }
    }
}

