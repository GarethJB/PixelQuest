package com.jb.pixelquest.data.local.android.datasource

import com.jb.pixelquest.data.local.android.dao.ArtworkDao
import com.jb.pixelquest.data.local.android.mapper.ArtworkMapper.toDomain
import com.jb.pixelquest.data.local.android.mapper.ArtworkMapper.toEntity
import com.jb.pixelquest.shared.domain.model.artwork.Artwork
import com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory

/**
 * 작품 Local DataSource 구현체
 */
class ArtworkLocalDataSource(
    private val artworkDao: ArtworkDao
) {
    suspend fun getArtworkById(id: String): Artwork? {
        return artworkDao.getArtworkById(id)?.toDomain()
    }

    suspend fun getArtworksByCategory(category: ArtworkCategory, limit: Int = 20): List<Artwork> {
        return artworkDao.getArtworksByCategory(category.name, limit)
            .map { it.toDomain() }
    }

    suspend fun getBookmarkedArtworks(): List<Artwork> {
        return artworkDao.getBookmarkedArtworks()
            .map { it.toDomain() }
    }

    suspend fun getLikedArtworks(): List<Artwork> {
        return artworkDao.getLikedArtworks()
            .map { it.toDomain() }
    }

    suspend fun getDraftArtworks(): List<Artwork> {
        return artworkDao.getDraftArtworks()
            .map { it.toDomain() }
    }

    suspend fun getAllArtworks(): List<Artwork> {
        return artworkDao.getAllArtworks()
            .map { it.toDomain() }
    }

    suspend fun saveArtwork(artwork: Artwork) {
        artworkDao.insertArtwork(artwork.toEntity())
    }

    suspend fun saveArtworks(artworks: List<Artwork>) {
        artworkDao.insertArtworks(artworks.map { it.toEntity() })
    }

    suspend fun updateArtwork(artwork: Artwork) {
        artworkDao.updateArtwork(artwork.toEntity())
    }

    suspend fun deleteArtwork(id: String) {
        artworkDao.deleteArtwork(id)
    }

    suspend fun deleteAllArtworks() {
        artworkDao.deleteAllArtworks()
    }

    suspend fun clearOldArtworks(olderThanDays: Int = 30) {
        val timestamp = System.currentTimeMillis() - (olderThanDays * 24 * 60 * 60 * 1000L)
        artworkDao.deleteArtworksOlderThan(timestamp)
    }
}

