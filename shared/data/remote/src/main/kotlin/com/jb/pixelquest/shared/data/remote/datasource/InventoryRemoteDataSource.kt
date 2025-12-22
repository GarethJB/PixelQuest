package com.jb.pixelquest.shared.data.remote.datasource

import com.jb.pixelquest.shared.data.remote.api.PixelQuestApi
import com.jb.pixelquest.shared.data.remote.mapper.InventoryMapper.toDomain
import com.jb.pixelquest.shared.domain.model.inventory.InventoryItem
import com.jb.pixelquest.shared.domain.model.inventory.InventoryItemType

/**
 * 인벤토리 Remote DataSource
 */
class InventoryRemoteDataSource(
    private val api: PixelQuestApi
) {
    suspend fun getInventoryItems(): Result<List<InventoryItem>> {
        return try {
            val response = api.getInventoryItems()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getInventoryItemsByType(type: InventoryItemType): Result<List<InventoryItem>> {
        return try {
            val response = api.getInventoryItemsByType(type.name)
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getInventoryItemById(id: String): Result<InventoryItem> {
        return try {
            val response = api.getInventoryItemById(id)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun equipItem(itemId: String): Result<InventoryItem> {
        return try {
            val response = api.equipItem(itemId)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun unequipItem(itemId: String): Result<InventoryItem> {
        return try {
            val response = api.unequipItem(itemId)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getEquippedItems(): Result<List<InventoryItem>> {
        return try {
            val response = api.getEquippedItems()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

