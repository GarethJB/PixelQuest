package com.jb.pixelquest.shared.domain.usecase.artwork

import com.jb.pixelquest.shared.domain.model.artwork.Artwork
import com.jb.pixelquest.shared.domain.repository.artwork.ArtworkRepository

/**
 * 내 작품 목록 조회 UseCase
 */
class GetMyArtworksUseCase(
    private val artworkRepository: ArtworkRepository
) {
    suspend operator fun invoke(): Result<List<Artwork>> {
        return artworkRepository.getMyArtworks()
    }
}

