package com.jb.pixelquest.shared.domain.usecase.artwork

import com.jb.pixelquest.shared.domain.model.artwork.Artwork
import com.jb.pixelquest.shared.domain.repository.artwork.ArtworkRepository

/**
 * 작품 검색 UseCase
 */
class SearchArtworksUseCase(
    private val artworkRepository: ArtworkRepository
) {
    suspend operator fun invoke(
        query: String,
        limit: Int = 20
    ): Result<List<Artwork>> {
        return artworkRepository.searchArtworks(query, limit)
    }
}

