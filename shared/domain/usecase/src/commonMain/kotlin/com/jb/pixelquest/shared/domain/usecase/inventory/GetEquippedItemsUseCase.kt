package com.jb.pixelquest.shared.domain.usecase.inventory

import com.jb.pixelquest.shared.domain.model.inventory.InventoryItem
import com.jb.pixelquest.shared.domain.repository.inventory.InventoryRepository

/**
 * 장착된 아이템 목록 조회 UseCase
 */
class GetEquippedItemsUseCase(
    private val inventoryRepository: InventoryRepository
) {
    suspend operator fun invoke(): Result<List<InventoryItem>> {
        return inventoryRepository.getEquippedItems()
    }
}

