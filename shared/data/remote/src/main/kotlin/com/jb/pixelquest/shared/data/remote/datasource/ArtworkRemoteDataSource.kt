package com.jb.pixelquest.shared.data.remote.datasource

import com.jb.pixelquest.shared.data.remote.api.PixelQuestApi
import com.jb.pixelquest.shared.data.remote.dto.ArtworkDto
import com.jb.pixelquest.shared.data.remote.mapper.ArtworkMapper.toDomain
import com.jb.pixelquest.shared.data.remote.mapper.ArtworkMapper.toDto
import com.jb.pixelquest.shared.domain.model.artwork.Artwork
import com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory

/**
 * 작품 Remote DataSource
 */
class ArtworkRemoteDataSource(
    private val api: PixelQuestApi
) {
    suspend fun getTrendingArtworks(limit: Int = 20): Result<List<Artwork>> {
        return try {
            val response = api.getTrendingArtworks(limit)
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getLatestArtworks(limit: Int = 20): Result<List<Artwork>> {
        return try {
            val response = api.getLatestArtworks(limit)
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getCategoryArtworks(
        category: ArtworkCategory,
        limit: Int = 20
    ): Result<List<Artwork>> {
        return try {
            val response = api.getCategoryArtworks(category.name, limit)
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun searchArtworks(query: String, limit: Int = 20): Result<List<Artwork>> {
        return try {
            val response = api.searchArtworks(query, limit)
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getArtworkById(id: String): Result<Artwork> {
        return try {
            val response = api.getArtworkById(id)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getMyArtworks(): Result<List<Artwork>> {
        return try {
            val response = api.getMyArtworks()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun createArtwork(artwork: Artwork): Result<Artwork> {
        return try {
            val dto = artwork.toDto()
            val response = api.createArtwork(dto)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateArtwork(artwork: Artwork): Result<Artwork> {
        return try {
            val dto = artwork.toDto()
            val response = api.updateArtwork(artwork.id, dto)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun deleteArtwork(id: String): Result<Unit> {
        return try {
            api.deleteArtwork(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun toggleLike(artworkId: String): Result<Boolean> {
        return try {
            val response = api.toggleLike(artworkId)
            Result.success(response["isLiked"] ?: false)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun toggleBookmark(artworkId: String): Result<Boolean> {
        return try {
            val response = api.toggleBookmark(artworkId)
            Result.success(response["isBookmarked"] ?: false)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun toggleArtworkVisibility(artworkId: String): Result<Boolean> {
        return try {
            val response = api.toggleArtworkVisibility(artworkId)
            Result.success(response["isPublished"] ?: false)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

