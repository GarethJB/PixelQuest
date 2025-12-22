package com.jb.pixelquest.shared.domain.usecase.artwork

import com.jb.pixelquest.shared.domain.model.artwork.Artwork
import com.jb.pixelquest.shared.domain.repository.artwork.ArtworkRepository

/**
 * 작품 수정 UseCase
 */
class UpdateArtworkUseCase(
    private val artworkRepository: ArtworkRepository
) {
    suspend operator fun invoke(artwork: Artwork): Result<Artwork> {
        return artworkRepository.updateArtwork(artwork)
    }
}

