package com.jb.pixelquest.shared.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * 캔버스 DTO
 */
data class CanvasDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnailPath")
    val thumbnailPath: String? = null,
    @SerializedName("lastModified")
    val lastModified: Long,
    @SerializedName("canvasWidth")
    val canvasWidth: Int,
    @SerializedName("canvasHeight")
    val canvasHeight: Int,
    @SerializedName("filePath")
    val filePath: String? = null
)

/**
 * 팔레트 DTO
 */
data class PaletteDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("colors")
    val colors: List<String>, // Hex color strings
    @SerializedName("isDefault")
    val isDefault: Boolean = false
)

/**
 * 브러시 DTO
 */
data class BrushDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("shape")
    val shape: String, // "CIRCLE", "SQUARE", "DIAMOND"
    @SerializedName("previewImagePath")
    val previewImagePath: String?
)

