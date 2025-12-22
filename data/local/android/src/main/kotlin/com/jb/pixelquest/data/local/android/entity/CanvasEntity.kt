package com.jb.pixelquest.data.local.android.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 캔버스 Room Entity
 */
@Entity(tableName = "canvases")
data class CanvasEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val thumbnailPath: String? = null,
    val lastModified: Long,
    val canvasWidth: Int,
    val canvasHeight: Int,
    val filePath: String? = null
)

