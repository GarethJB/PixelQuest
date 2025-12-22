package com.jb.pixelquest.shared.domain.usecase.studio

import com.jb.pixelquest.shared.domain.model.studio.Canvas
import com.jb.pixelquest.shared.domain.repository.studio.StudioRepository

/**
 * 캔버스 목록 조회 UseCase
 */
class GetCanvasesUseCase(
    private val studioRepository: StudioRepository
) {
    suspend operator fun invoke(): Result<List<Canvas>> {
        return studioRepository.getCanvases()
    }
}

