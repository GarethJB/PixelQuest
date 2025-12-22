package com.jb.pixelquest.shared.domain.usecase.studio

import com.jb.pixelquest.shared.domain.model.studio.Canvas
import com.jb.pixelquest.shared.domain.repository.studio.StudioRepository

/**
 * 캔버스 ID로 조회 UseCase
 */
class GetCanvasByIdUseCase(
    private val studioRepository: StudioRepository
) {
    suspend operator fun invoke(id: String): Result<Canvas> {
        return studioRepository.getCanvasById(id)
    }
}

