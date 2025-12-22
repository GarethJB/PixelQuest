package com.jb.pixelquest.shared.domain.usecase.artwork

import com.jb.pixelquest.shared.domain.repository.artwork.ArtworkRepository

/**
 * 작품 북마크 토글 UseCase
 */
class ToggleBookmarkUseCase(
    private val artworkRepository: ArtworkRepository
) {
    suspend operator fun invoke(artworkId: String): Result<Boolean> {
        return artworkRepository.toggleBookmark(artworkId)
    }
}

