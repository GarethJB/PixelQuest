package com.jb.pixelquest.shared.domain.usecase.studio

import com.jb.pixelquest.shared.domain.model.studio.Canvas
import com.jb.pixelquest.shared.domain.repository.studio.StudioRepository

/**
 * 캔버스 생성 UseCase
 */
class CreateCanvasUseCase(
    private val studioRepository: StudioRepository
) {
    suspend operator fun invoke(canvas: Canvas): Result<Canvas> {
        return studioRepository.createCanvas(canvas)
    }
}

