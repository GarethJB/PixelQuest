package com.jb.pixelquest.shared.domain.usecase.inventory

import com.jb.pixelquest.shared.domain.model.inventory.InventoryItem
import com.jb.pixelquest.shared.domain.repository.inventory.InventoryRepository

/**
 * 인벤토리 아이템 ID로 조회 UseCase
 */
class GetInventoryItemByIdUseCase(
    private val inventoryRepository: InventoryRepository
) {
    suspend operator fun invoke(id: String): Result<InventoryItem> {
        return inventoryRepository.getInventoryItemById(id)
    }
}

