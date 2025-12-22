package com.jb.pixelquest.shared.domain.usecase.studio

import com.jb.pixelquest.shared.domain.model.studio.Canvas
import com.jb.pixelquest.shared.domain.repository.studio.StudioRepository

/**
 * 캔버스 수정 UseCase
 */
class UpdateCanvasUseCase(
    private val studioRepository: StudioRepository
) {
    suspend operator fun invoke(canvas: Canvas): Result<Canvas> {
        return studioRepository.updateCanvas(canvas)
    }
}

