package com.jb.pixelquest.shared.domain.usecase.artwork

import com.jb.pixelquest.shared.domain.model.artwork.Artwork
import com.jb.pixelquest.shared.domain.repository.artwork.ArtworkRepository

/**
 * 최신 작품 목록 조회 UseCase
 */
class GetLatestArtworksUseCase(
    private val artworkRepository: ArtworkRepository
) {
    suspend operator fun invoke(limit: Int = 20): Result<List<Artwork>> {
        return artworkRepository.getLatestArtworks(limit)
    }
}

