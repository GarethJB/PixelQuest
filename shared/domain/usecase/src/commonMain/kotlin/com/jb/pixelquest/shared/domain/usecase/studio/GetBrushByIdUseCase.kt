package com.jb.pixelquest.shared.domain.usecase.studio

import com.jb.pixelquest.shared.domain.model.studio.Brush
import com.jb.pixelquest.shared.domain.repository.studio.StudioRepository

/**
 * 브러시 ID로 조회 UseCase
 */
class GetBrushByIdUseCase(
    private val studioRepository: StudioRepository
) {
    suspend operator fun invoke(id: String): Result<Brush> {
        return studioRepository.getBrushById(id)
    }
}

