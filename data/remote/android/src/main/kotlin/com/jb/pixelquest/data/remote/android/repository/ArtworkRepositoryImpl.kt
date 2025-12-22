package com.jb.pixelquest.data.remote.android.repository

import com.jb.pixelquest.data.local.android.datasource.ArtworkLocalDataSource
import com.jb.pixelquest.shared.data.remote.datasource.ArtworkRemoteDataSource
import com.jb.pixelquest.shared.domain.model.artwork.Artwork
import com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory
import com.jb.pixelquest.shared.domain.repository.artwork.ArtworkRepository
import javax.inject.Inject

/**
 * 작품 Repository 구현체
 * Remote와 Local DataSource를 조합하여 구현
 */
class ArtworkRepositoryImpl @Inject constructor(
    private val remoteDataSource: ArtworkRemoteDataSource,
    private val localDataSource: ArtworkLocalDataSource
) : ArtworkRepository {
    
    override suspend fun getTrendingArtworks(limit: Int): Result<List<Artwork>> {
        return remoteDataSource.getTrendingArtworks(limit)
            .onSuccess { artworks ->
                localDataSource.saveArtworks(artworks)
            }
            .onFailure {
                val localArtworks = localDataSource.getAllArtworks().take(limit)
                if (localArtworks.isNotEmpty()) {
                    return Result.success(localArtworks)
                }
            }
    }

    override suspend fun getLatestArtworks(limit: Int): Result<List<Artwork>> {
        return remoteDataSource.getLatestArtworks(limit)
            .onSuccess { artworks ->
                localDataSource.saveArtworks(artworks)
            }
            .onFailure {
                val localArtworks = localDataSource.getAllArtworks().take(limit)
                if (localArtworks.isNotEmpty()) {
                    return Result.success(localArtworks)
                }
            }
    }

    override suspend fun getCategoryArtworks(
        category: ArtworkCategory,
        limit: Int
    ): Result<List<Artwork>> {
        return remoteDataSource.getCategoryArtworks(category, limit)
            .onSuccess { artworks ->
                localDataSource.saveArtworks(artworks)
            }
            .onFailure {
                val localArtworks = localDataSource.getArtworksByCategory(category, limit)
                if (localArtworks.isNotEmpty()) {
                    return Result.success(localArtworks)
                }
            }
    }

    override suspend fun searchArtworks(query: String, limit: Int): Result<List<Artwork>> {
        return remoteDataSource.searchArtworks(query, limit)
            .onSuccess { artworks ->
                localDataSource.saveArtworks(artworks)
            }
    }

    override suspend fun getArtworkById(id: String): Result<Artwork> {
        return remoteDataSource.getArtworkById(id)
            .onSuccess { artwork ->
                localDataSource.saveArtwork(artwork)
            }
            .onFailure {
                val localArtwork = localDataSource.getArtworkById(id)
                if (localArtwork != null) {
                    return Result.success(localArtwork)
                }
            }
    }

    override suspend fun getMyArtworks(): Result<List<Artwork>> {
        return remoteDataSource.getMyArtworks()
            .onSuccess { artworks ->
                localDataSource.saveArtworks(artworks)
            }
            .onFailure {
                val localArtworks = localDataSource.getDraftArtworks() + 
                    localDataSource.getAllArtworks().filter { it.isPublished }
                if (localArtworks.isNotEmpty()) {
                    return Result.success(localArtworks)
                }
            }
    }

    override suspend fun createArtwork(artwork: Artwork): Result<Artwork> {
        return remoteDataSource.createArtwork(artwork)
            .onSuccess { createdArtwork ->
                localDataSource.saveArtwork(createdArtwork)
            }
    }

    override suspend fun updateArtwork(artwork: Artwork): Result<Artwork> {
        return remoteDataSource.updateArtwork(artwork)
            .onSuccess { updatedArtwork ->
                localDataSource.updateArtwork(updatedArtwork)
            }
    }

    override suspend fun deleteArtwork(id: String): Result<Unit> {
        return remoteDataSource.deleteArtwork(id)
            .onSuccess {
                localDataSource.deleteArtwork(id)
            }
    }

    override suspend fun toggleLike(artworkId: String): Result<Boolean> {
        return remoteDataSource.toggleLike(artworkId)
            .onSuccess { isLiked ->
                // 로컬 데이터 업데이트
                localDataSource.getArtworkById(artworkId)?.let { artwork ->
                    val updatedArtwork = artwork.copy(isLiked = isLiked)
                    localDataSource.updateArtwork(updatedArtwork)
                }
            }
    }

    override suspend fun toggleBookmark(artworkId: String): Result<Boolean> {
        return remoteDataSource.toggleBookmark(artworkId)
            .onSuccess { isBookmarked ->
                localDataSource.getArtworkById(artworkId)?.let { artwork ->
                    val updatedArtwork = artwork.copy(isBookmarked = isBookmarked)
                    localDataSource.updateArtwork(updatedArtwork)
                }
            }
    }

    override suspend fun toggleArtworkVisibility(artworkId: String): Result<Boolean> {
        return remoteDataSource.toggleArtworkVisibility(artworkId)
            .onSuccess { isPublished ->
                localDataSource.getArtworkById(artworkId)?.let { artwork ->
                    val updatedArtwork = artwork.copy(
                        isPublished = isPublished,
                        isDraft = !isPublished
                    )
                    localDataSource.updateArtwork(updatedArtwork)
                }
            }
    }
}

