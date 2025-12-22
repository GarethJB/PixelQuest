package com.jb.pixelquest.shared.domain.usecase.artwork

import com.jb.pixelquest.shared.domain.repository.artwork.ArtworkRepository

/**
 * 작품 삭제 UseCase
 */
class DeleteArtworkUseCase(
    private val artworkRepository: ArtworkRepository
) {
    suspend operator fun invoke(id: String): Result<Unit> {
        return artworkRepository.deleteArtwork(id)
    }
}

