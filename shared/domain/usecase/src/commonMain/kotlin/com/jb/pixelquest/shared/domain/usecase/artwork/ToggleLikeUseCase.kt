package com.jb.pixelquest.shared.domain.usecase.artwork

import com.jb.pixelquest.shared.domain.repository.artwork.ArtworkRepository

/**
 * 작품 좋아요 토글 UseCase
 */
class ToggleLikeUseCase(
    private val artworkRepository: ArtworkRepository
) {
    suspend operator fun invoke(artworkId: String): Result<Boolean> {
        return artworkRepository.toggleLike(artworkId)
    }
}

