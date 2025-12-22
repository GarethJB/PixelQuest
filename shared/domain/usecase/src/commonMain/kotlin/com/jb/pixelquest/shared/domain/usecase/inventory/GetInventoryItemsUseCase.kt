package com.jb.pixelquest.shared.domain.usecase.inventory

import com.jb.pixelquest.shared.domain.model.inventory.InventoryItem
import com.jb.pixelquest.shared.domain.repository.inventory.InventoryRepository

/**
 * 전체 인벤토리 아이템 조회 UseCase
 */
class GetInventoryItemsUseCase(
    private val inventoryRepository: InventoryRepository
) {
    suspend operator fun invoke(): Result<List<InventoryItem>> {
        return inventoryRepository.getInventoryItems()
    }
}

