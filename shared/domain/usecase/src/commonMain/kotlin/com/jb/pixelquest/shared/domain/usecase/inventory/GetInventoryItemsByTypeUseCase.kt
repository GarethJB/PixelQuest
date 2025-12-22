package com.jb.pixelquest.shared.domain.usecase.inventory

import com.jb.pixelquest.shared.domain.model.inventory.InventoryItem
import com.jb.pixelquest.shared.domain.model.inventory.InventoryItemType
import com.jb.pixelquest.shared.domain.repository.inventory.InventoryRepository

/**
 * 타입별 인벤토리 아이템 조회 UseCase
 */
class GetInventoryItemsByTypeUseCase(
    private val inventoryRepository: InventoryRepository
) {
    suspend operator fun invoke(type: InventoryItemType): Result<List<InventoryItem>> {
        return inventoryRepository.getInventoryItemsByType(type)
    }
}

