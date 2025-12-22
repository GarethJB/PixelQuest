package com.jb.pixelquest.shared.domain.usecase.artwork

import com.jb.pixelquest.shared.domain.model.artwork.Artwork
import com.jb.pixelquest.shared.domain.repository.artwork.ArtworkRepository

/**
 * 작품 생성 UseCase
 */
class CreateArtworkUseCase(
    private val artworkRepository: ArtworkRepository
) {
    suspend operator fun invoke(artwork: Artwork): Result<Artwork> {
        return artworkRepository.createArtwork(artwork)
    }
}

