package com.jb.pixelquest.shared.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * 작품 DTO
 */
data class ArtworkDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("author")
    val author: UserDto,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("views")
    val views: Int,
    @SerializedName("comments")
    val comments: Int,
    @SerializedName("createdAt")
    val createdAt: Long,
    @SerializedName("updatedAt")
    val updatedAt: Long,
    @SerializedName("canvasWidth")
    val canvasWidth: Int,
    @SerializedName("canvasHeight")
    val canvasHeight: Int,
    @SerializedName("palette")
    val palette: List<String>? = null, // Hex color strings
    @SerializedName("questId")
    val questId: String? = null,
    @SerializedName("isCollaborative")
    val isCollaborative: Boolean = false,
    @SerializedName("contributors")
    val contributors: List<UserDto> = emptyList(),
    @SerializedName("isPublished")
    val isPublished: Boolean = false,
    @SerializedName("isDraft")
    val isDraft: Boolean = false,
    @SerializedName("isLiked")
    val isLiked: Boolean = false,
    @SerializedName("isBookmarked")
    val isBookmarked: Boolean = false
)

