package com.jb.pixelquest.shared.data.remote.datasource

import com.jb.pixelquest.shared.domain.model.artwork.Artwork
import com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory

/**
 * 작품 Remote DataSource 인터페이스
 */
interface ArtworkRemoteDataSource {
    suspend fun getTrendingArtworks(limit: Int = 20): Result<List<Artwork>>
    suspend fun getLatestArtworks(limit: Int = 20): Result<List<Artwork>>
    suspend fun getCategoryArtworks(
        category: ArtworkCategory,
        limit: Int = 20
    ): Result<List<Artwork>>
    suspend fun searchArtworks(query: String, limit: Int = 20): Result<List<Artwork>>
    suspend fun getArtworkById(id: String): Result<Artwork>
    suspend fun getMyArtworks(): Result<List<Artwork>>
    suspend fun createArtwork(artwork: Artwork): Result<Artwork>
    suspend fun updateArtwork(artwork: Artwork): Result<Artwork>
    suspend fun deleteArtwork(id: String): Result<Unit>
    suspend fun toggleLike(artworkId: String): Result<Boolean>
    suspend fun toggleBookmark(artworkId: String): Result<Boolean>
    suspend fun toggleArtworkVisibility(artworkId: String): Result<Boolean>
}
