package com.jb.pixelquest.shared.domain.usecase.inventory

import com.jb.pixelquest.shared.domain.model.inventory.InventoryItem
import com.jb.pixelquest.shared.domain.repository.inventory.InventoryRepository

/**
 * 아이템 장착 UseCase
 */
class EquipItemUseCase(
    private val inventoryRepository: InventoryRepository
) {
    suspend operator fun invoke(itemId: String): Result<InventoryItem> {
        return inventoryRepository.equipItem(itemId)
    }
}

