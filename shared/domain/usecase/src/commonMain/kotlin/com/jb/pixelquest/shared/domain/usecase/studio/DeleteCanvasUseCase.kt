package com.jb.pixelquest.shared.domain.usecase.studio

import com.jb.pixelquest.shared.domain.repository.studio.StudioRepository

/**
 * 캔버스 삭제 UseCase
 */
class DeleteCanvasUseCase(
    private val studioRepository: StudioRepository
) {
    suspend operator fun invoke(id: String): Result<Unit> {
        return studioRepository.deleteCanvas(id)
    }
}

