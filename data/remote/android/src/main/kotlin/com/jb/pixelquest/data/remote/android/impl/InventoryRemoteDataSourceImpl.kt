package com.jb.pixelquest.data.remote.android.impl

import com.jb.pixelquest.data.remote.android.api.PixelQuestApi
import com.jb.pixelquest.shared.data.remote.datasource.InventoryRemoteDataSource
import com.jb.pixelquest.shared.data.remote.mapper.InventoryMapper.toDomain
import com.jb.pixelquest.shared.domain.model.inventory.InventoryItem
import com.jb.pixelquest.shared.domain.model.inventory.InventoryItemType
import javax.inject.Inject

/**
 * 인벤토리 Remote DataSource 구현체
 */
class InventoryRemoteDataSourceImpl @Inject constructor(
    private val api: PixelQuestApi
) : InventoryRemoteDataSource {
    override suspend fun getInventoryItems(): Result<List<InventoryItem>> {
        return try {
            val response = api.getInventoryItems()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getInventoryItemsByType(type: InventoryItemType): Result<List<InventoryItem>> {
        return try {
            val response = api.getInventoryItemsByType(type.name)
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getInventoryItemById(id: String): Result<InventoryItem> {
        return try {
            val response = api.getInventoryItemById(id)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun equipItem(itemId: String): Result<InventoryItem> {
        return try {
            val response = api.equipItem(itemId)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun unequipItem(itemId: String): Result<InventoryItem> {
        return try {
            val response = api.unequipItem(itemId)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getEquippedItems(): Result<List<InventoryItem>> {
        return try {
            val response = api.getEquippedItems()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

