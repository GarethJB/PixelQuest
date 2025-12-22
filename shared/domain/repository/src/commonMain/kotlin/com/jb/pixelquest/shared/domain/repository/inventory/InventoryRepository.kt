package com.jb.pixelquest.shared.domain.repository.inventory

import com.jb.pixelquest.shared.domain.model.inventory.InventoryItem
import com.jb.pixelquest.shared.domain.model.inventory.InventoryItemType

/**
 * 인벤토리 관련 데이터를 관리하는 Repository 인터페이스
 */
interface InventoryRepository {
    /**
     * 전체 인벤토리 아이템 조회
     */
    suspend fun getInventoryItems(): Result<List<InventoryItem>>

    /**
     * 타입별 인벤토리 아이템 조회
     */
    suspend fun getInventoryItemsByType(type: InventoryItemType): Result<List<InventoryItem>>

    /**
     * 인벤토리 아이템 ID로 조회
     */
    suspend fun getInventoryItemById(id: String): Result<InventoryItem>

    /**
     * 아이템 장착
     */
    suspend fun equipItem(itemId: String): Result<InventoryItem>

    /**
     * 아이템 장착 해제
     */
    suspend fun unequipItem(itemId: String): Result<InventoryItem>

    /**
     * 장착된 아이템 목록 조회
     */
    suspend fun getEquippedItems(): Result<List<InventoryItem>>
}

