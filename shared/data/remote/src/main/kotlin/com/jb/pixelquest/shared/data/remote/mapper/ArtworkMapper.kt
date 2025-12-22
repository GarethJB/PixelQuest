package com.jb.pixelquest.shared.data.remote.mapper

import com.jb.pixelquest.shared.data.remote.dto.ArtworkDto
import com.jb.pixelquest.shared.domain.model.artwork.Artwork
import com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory
import com.jb.pixelquest.shared.domain.model.common.CanvasSize
import com.jb.pixelquest.shared.domain.model.common.Color

/**
 * Artwork DTO ↔ Domain 모델 Mapper
 */
object ArtworkMapper {
    
    fun ArtworkDto.toDomain(): Artwork {
        return Artwork(
            id = id,
            title = title,
            description = description,
            author = UserMapper.run { author.toDomain() },
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
            palette = palette?.map { Color.fromHex(it) },
            questId = questId,
            isCollaborative = isCollaborative,
            contributors = contributors.map { UserMapper.run { it.toDomain() } },
            isPublished = isPublished,
            isDraft = isDraft,
            isLiked = isLiked,
            isBookmarked = isBookmarked
        )
    }
    
    fun Artwork.toDto(): ArtworkDto {
        return ArtworkDto(
            id = id,
            title = title,
            description = description,
            author = UserMapper.run { author.toDto() },
            imageUrl = imageUrl,
            thumbnailUrl = thumbnailUrl,
            category = category.name,
            tags = tags,
            likes = likes,
            views = views,
            comments = comments,
            createdAt = createdAt,
            updatedAt = updatedAt,
            canvasWidth = canvasSize.width,
            canvasHeight = canvasSize.height,
            palette = palette?.map { it.toHexString() },
            questId = questId,
            isCollaborative = isCollaborative,
            contributors = contributors.map { UserMapper.run { it.toDto() } },
            isPublished = isPublished,
            isDraft = isDraft,
            isLiked = isLiked,
            isBookmarked = isBookmarked
        )
    }
    
    private fun String.toArtworkCategory(): ArtworkCategory {
        return try {
            ArtworkCategory.valueOf(this.uppercase())
        } catch (e: IllegalArgumentException) {
            ArtworkCategory.ABSTRACT // 기본값
        }
    }
}

