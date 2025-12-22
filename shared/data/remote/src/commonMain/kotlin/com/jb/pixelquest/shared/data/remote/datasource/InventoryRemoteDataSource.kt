package com.jb.pixelquest.shared.data.remote.datasource

import com.jb.pixelquest.shared.domain.model.inventory.InventoryItem
import com.jb.pixelquest.shared.domain.model.inventory.InventoryItemType

/**
 * 인벤토리 Remote DataSource 인터페이스
 */
interface InventoryRemoteDataSource {
    suspend fun getInventoryItems(): Result<List<InventoryItem>>
    suspend fun getInventoryItemsByType(type: InventoryItemType): Result<List<InventoryItem>>
    suspend fun getInventoryItemById(id: String): Result<InventoryItem>
    suspend fun equipItem(itemId: String): Result<InventoryItem>
    suspend fun unequipItem(itemId: String): Result<InventoryItem>
    suspend fun getEquippedItems(): Result<List<InventoryItem>>
}
