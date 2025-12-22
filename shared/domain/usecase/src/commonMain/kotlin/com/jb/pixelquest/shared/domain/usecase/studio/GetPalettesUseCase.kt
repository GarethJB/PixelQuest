package com.jb.pixelquest.shared.domain.usecase.studio

import com.jb.pixelquest.shared.domain.model.studio.Palette
import com.jb.pixelquest.shared.domain.repository.studio.StudioRepository

/**
 * 팔레트 목록 조회 UseCase
 */
class GetPalettesUseCase(
    private val studioRepository: StudioRepository
) {
    suspend operator fun invoke(): Result<List<Palette>> {
        return studioRepository.getPalettes()
    }
}

