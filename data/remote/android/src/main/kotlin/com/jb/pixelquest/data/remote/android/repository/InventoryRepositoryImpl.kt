package com.jb.pixelquest.data.remote.android.repository

import com.jb.pixelquest.shared.data.remote.datasource.InventoryRemoteDataSource
import com.jb.pixelquest.shared.domain.model.inventory.InventoryItem
import com.jb.pixelquest.shared.domain.model.inventory.InventoryItemType
import com.jb.pixelquest.shared.domain.repository.inventory.InventoryRepository
import javax.inject.Inject

/**
 * 인벤토리 Repository 구현체
 * Remote DataSource를 사용하여 구현
 */
class InventoryRepositoryImpl @Inject constructor(
    private val remoteDataSource: InventoryRemoteDataSource
) : InventoryRepository {
    
    override suspend fun getInventoryItems(): Result<List<InventoryItem>> {
        return remoteDataSource.getInventoryItems()
    }

    override suspend fun getInventoryItemsByType(type: InventoryItemType): Result<List<InventoryItem>> {
        return remoteDataSource.getInventoryItemsByType(type)
    }

    override suspend fun getInventoryItemById(id: String): Result<InventoryItem> {
        return remoteDataSource.getInventoryItemById(id)
    }

    override suspend fun equipItem(itemId: String): Result<InventoryItem> {
        return remoteDataSource.equipItem(itemId)
    }

    override suspend fun unequipItem(itemId: String): Result<InventoryItem> {
        return remoteDataSource.unequipItem(itemId)
    }

    override suspend fun getEquippedItems(): Result<List<InventoryItem>> {
        return remoteDataSource.getEquippedItems()
    }
}

