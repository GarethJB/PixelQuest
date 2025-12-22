package com.jb.pixelquest.data.local.android.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jb.pixelquest.data.local.android.entity.CanvasEntity
import kotlinx.coroutines.flow.Flow

/**
 * 캔버스 DAO
 */
@Dao
interface CanvasDao {
    @Query("SELECT * FROM canvases WHERE id = :id")
    suspend fun getCanvasById(id: String): CanvasEntity?

    @Query("SELECT * FROM canvases ORDER BY lastModified DESC")
    suspend fun getAllCanvases(): List<CanvasEntity>

    @Query("SELECT * FROM canvases ORDER BY lastModified DESC")
    fun observeAllCanvases(): Flow<List<CanvasEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCanvas(canvas: CanvasEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCanvases(canvases: List<CanvasEntity>)

    @Update
    suspend fun updateCanvas(canvas: CanvasEntity)

    @Query("DELETE FROM canvases WHERE id = :id")
    suspend fun deleteCanvas(id: String)

    @Query("DELETE FROM canvases")
    suspend fun deleteAllCanvases()
}

