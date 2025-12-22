package com.jb.pixelquest.data.local.android.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jb.pixelquest.data.local.android.entity.ArtworkEntity
import kotlinx.coroutines.flow.Flow

/**
 * 작품 DAO
 */
@Dao
interface ArtworkDao {
    @Query("SELECT * FROM artworks WHERE id = :id")
    suspend fun getArtworkById(id: String): ArtworkEntity?

    @Query("SELECT * FROM artworks WHERE category = :category ORDER BY cachedAt DESC LIMIT :limit")
    suspend fun getArtworksByCategory(category: String, limit: Int = 20): List<ArtworkEntity>

    @Query("SELECT * FROM artworks WHERE isBookmarked = 1 ORDER BY cachedAt DESC")
    suspend fun getBookmarkedArtworks(): List<ArtworkEntity>

    @Query("SELECT * FROM artworks WHERE isLiked = 1 ORDER BY cachedAt DESC")
    suspend fun getLikedArtworks(): List<ArtworkEntity>

    @Query("SELECT * FROM artworks WHERE isDraft = 1 ORDER BY lastModified DESC")
    suspend fun getDraftArtworks(): List<ArtworkEntity>

    @Query("SELECT * FROM artworks")
    suspend fun getAllArtworks(): List<ArtworkEntity>

    @Query("SELECT * FROM artworks")
    fun observeAllArtworks(): Flow<List<ArtworkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtwork(artwork: ArtworkEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtworks(artworks: List<ArtworkEntity>)

    @Update
    suspend fun updateArtwork(artwork: ArtworkEntity)

    @Query("DELETE FROM artworks WHERE id = :id")
    suspend fun deleteArtwork(id: String)

    @Query("DELETE FROM artworks")
    suspend fun deleteAllArtworks()

    @Query("DELETE FROM artworks WHERE cachedAt < :timestamp")
    suspend fun deleteArtworksOlderThan(timestamp: Long)
}

