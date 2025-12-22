package com.jb.pixelquest.shared.domain.usecase.artwork

import com.jb.pixelquest.shared.domain.repository.artwork.ArtworkRepository

/**
 * 작품 공개/비공개 토글 UseCase
 */
class ToggleArtworkVisibilityUseCase(
    private val artworkRepository: ArtworkRepository
) {
    suspend operator fun invoke(artworkId: String): Result<Boolean> {
        return artworkRepository.toggleArtworkVisibility(artworkId)
    }
}

