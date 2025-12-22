package com.jb.pixelquest.shared.data.remote.mapper

import com.jb.pixelquest.shared.data.remote.dto.InventoryItemDto
import com.jb.pixelquest.shared.domain.model.inventory.InventoryItem
import com.jb.pixelquest.shared.domain.model.inventory.InventoryItemType
import com.jb.pixelquest.shared.domain.model.inventory.ItemRarity

/**
 * Inventory DTO ↔ Domain 모델 Mapper
 */
object InventoryMapper {
    
    fun InventoryItemDto.toDomain(): InventoryItem {
        return InventoryItem(
            id = id,
            name = name,
            description = description,
            type = type.toInventoryItemType(),
            iconUrl = iconUrl,
            thumbnailUrl = thumbnailUrl,
            rarity = rarity.toItemRarity(),
            obtainedDate = obtainedDate,
            obtainedFrom = obtainedFrom,
            isEquipped = isEquipped,
            isNew = isNew,
            metadata = metadata
        )
    }
    
    private fun String.toInventoryItemType(): InventoryItemType {
        return try {
            InventoryItemType.valueOf(this.uppercase())
        } catch (e: IllegalArgumentException) {
            InventoryItemType.PALETTE
        }
    }
    
    private fun String.toItemRarity(): ItemRarity {
        return try {
            ItemRarity.valueOf(this.uppercase())
        } catch (e: IllegalArgumentException) {
            ItemRarity.COMMON
        }
    }
}

