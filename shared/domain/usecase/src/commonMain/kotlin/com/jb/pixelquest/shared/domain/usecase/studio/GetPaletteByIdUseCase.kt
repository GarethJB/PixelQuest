package com.jb.pixelquest.shared.domain.usecase.studio

import com.jb.pixelquest.shared.domain.model.studio.Palette
import com.jb.pixelquest.shared.domain.repository.studio.StudioRepository

/**
 * 팔레트 ID로 조회 UseCase
 */
class GetPaletteByIdUseCase(
    private val studioRepository: StudioRepository
) {
    suspend operator fun invoke(id: String): Result<Palette> {
        return studioRepository.getPaletteById(id)
    }
}

