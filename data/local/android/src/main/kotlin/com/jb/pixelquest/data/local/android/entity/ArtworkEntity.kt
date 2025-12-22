package com.jb.pixelquest.data.local.android.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.jb.pixelquest.data.local.android.converter.ArtworkTypeConverter

/**
 * 작품 Room Entity
 */
@Entity(tableName = "artworks")
@TypeConverters(ArtworkTypeConverter::class)
data class ArtworkEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String?,
    val authorJson: String, // User를 JSON으로 저장
    val imageUrl: String,
    val thumbnailUrl: String,
    val category: String, // ArtworkCategory.name
    val tagsJson: String, // List<String>을 JSON으로 저장
    val likes: Int,
    val views: Int,
    val comments: Int,
    val createdAt: Long,
    val updatedAt: Long,
    val canvasWidth: Int,
    val canvasHeight: Int,
    val paletteJson: String?, // List<Color>를 JSON으로 저장
    val questId: String?,
    val isCollaborative: Boolean = false,
    val contributorsJson: String, // List<User>를 JSON으로 저장
    val isPublished: Boolean = false,
    val isDraft: Boolean = false,
    val isLiked: Boolean = false,
    val isBookmarked: Boolean = false,
    val cachedAt: Long = System.currentTimeMillis()
)

