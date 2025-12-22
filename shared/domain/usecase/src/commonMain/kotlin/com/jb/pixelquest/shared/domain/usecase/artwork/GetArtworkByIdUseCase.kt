package com.jb.pixelquest.shared.domain.usecase.artwork

import com.jb.pixelquest.shared.domain.model.artwork.Artwork
import com.jb.pixelquest.shared.domain.repository.artwork.ArtworkRepository

/**
 * 작품 ID로 조회 UseCase
 */
class GetArtworkByIdUseCase(
    private val artworkRepository: ArtworkRepository
) {
    suspend operator fun invoke(id: String): Result<Artwork> {
        return artworkRepository.getArtworkById(id)
    }
}

