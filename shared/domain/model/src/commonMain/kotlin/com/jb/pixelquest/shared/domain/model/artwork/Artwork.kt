package com.jb.pixelquest.shared.domain.model.artwork

import com.jb.pixelquest.shared.domain.model.common.CanvasSize
import com.jb.pixelquest.shared.domain.model.common.Color
import com.jb.pixelquest.shared.domain.model.user.User

/**
 * 작품 도메인 모델
 */
data class Artwork(
    val id: String,
    val title: String,
    val description: String?,
    val author: User,
    val imageUrl: String,
    val thumbnailUrl: String,
    val category: ArtworkCategory,
    val tags: List<String>,
    val likes: Int,
    val views: Int,
    val comments: Int,
    val createdAt: Long,
    val updatedAt: Long,
    val canvasSize: CanvasSize,
    val palette: List<Color>? = null,
    val questId: String? = null,
    val isCollaborative: Boolean = false,
    val contributors: List<User> = emptyList(),
    val isPublished: Boolean = false,
    val isDraft: Boolean = false,
    val isLiked: Boolean = false,
    val isBookmarked: Boolean = false
)

