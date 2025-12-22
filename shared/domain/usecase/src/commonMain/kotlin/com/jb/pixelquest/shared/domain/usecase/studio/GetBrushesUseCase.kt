package com.jb.pixelquest.shared.domain.usecase.studio

import com.jb.pixelquest.shared.domain.model.studio.Brush
import com.jb.pixelquest.shared.domain.repository.studio.StudioRepository

/**
 * 브러시 목록 조회 UseCase
 */
class GetBrushesUseCase(
    private val studioRepository: StudioRepository
) {
    suspend operator fun invoke(): Result<List<Brush>> {
        return studioRepository.getBrushes()
    }
}

