package com.jb.pixelquest.shared.domain.usecase.artwork

import com.jb.pixelquest.shared.domain.model.artwork.Artwork
import com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory
import com.jb.pixelquest.shared.domain.repository.artwork.ArtworkRepository

/**
 * 카테고리별 작품 목록 조회 UseCase
 */
class GetCategoryArtworksUseCase(
    private val artworkRepository: ArtworkRepository
) {
    suspend operator fun invoke(
        category: ArtworkCategory,
        limit: Int = 20
    ): Result<List<Artwork>> {
        return artworkRepository.getCategoryArtworks(category, limit)
    }
}

