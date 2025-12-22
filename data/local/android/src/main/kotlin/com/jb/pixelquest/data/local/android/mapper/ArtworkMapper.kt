package com.jb.pixelquest.data.local.android.mapper

import com.google.gson.Gson
import com.jb.pixelquest.data.local.android.entity.ArtworkEntity
import com.jb.pixelquest.shared.domain.model.artwork.Artwork
import com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory
import com.jb.pixelquest.shared.domain.model.common.CanvasSize
import com.jb.pixelquest.shared.domain.model.common.Color
import com.jb.pixelquest.shared.domain.model.user.User

/**
 * Artwork Entity ↔ Domain 모델 Mapper
 */
object ArtworkMapper {
    private val gson = Gson()

    fun ArtworkEntity.toDomain(): Artwork {
        val author = gson.fromJson<User>(authorJson, User::class.java)
        val tags = gson.fromJson<List<String>>(
            tagsJson,
            object : com.google.gson.reflect.TypeToken<List<String>>() {}.type
        ) ?: emptyList()
        val palette = paletteJson?.let {
            gson.fromJson<List<Color>>(
                it,
                object : com.google.gson.reflect.TypeToken<List<Color>>() {}.type
            )
        }
        val contributors = gson.fromJson<List<User>>(
            contributorsJson,
            object : com.google.gson.reflect.TypeToken<List<User>>() {}.type
        ) ?: emptyList()

        return Artwork(
            id = id,
            title = title,
            description = description,
            author = author,
            imageUrl = imageUrl,
            thumbnailUrl = thumbnailUrl,
            category = category.toArtworkCategory(),
            tags = tags,
            likes = likes,
            views = views,
            comments = comments,
            createdAt = createdAt,
            updatedAt = updatedAt,
            canvasSize = CanvasSize(canvasWidth, canvasHeight),
            palette = palette,
            questId = questId,
            isCollaborative = isCollaborative,
            contributors = contributors,
            isPublished = isPublished,
            isDraft = isDraft,
            isLiked = isLiked,
            isBookmarked = isBookmarked
        )
    }

    fun Artwork.toEntity(): ArtworkEntity {
        return ArtworkEntity(
            id = id,
            title = title,
            description = description,
            authorJson = gson.toJson(author),
            imageUrl = imageUrl,
            thumbnailUrl = thumbnailUrl,
            category = category.name,
            tagsJson = gson.toJson(tags),
            likes = likes,
            views = views,
            comments = comments,
            createdAt = createdAt,
            updatedAt = updatedAt,
            canvasWidth = canvasSize.width,
            canvasHeight = canvasSize.height,
            paletteJson = palette?.let { gson.toJson(it) },
            questId = questId,
            isCollaborative = isCollaborative,
            contributorsJson = gson.toJson(contributors),
            isPublished = isPublished,
            isDraft = isDraft,
            isLiked = isLiked,
            isBookmarked = isBookmarked
        )
    }

    private fun String.toArtworkCategory(): ArtworkCategory {
        return try {
            ArtworkCategory.valueOf(this)
        } catch (e: IllegalArgumentException) {
            ArtworkCategory.ABSTRACT
        }
    }
}

