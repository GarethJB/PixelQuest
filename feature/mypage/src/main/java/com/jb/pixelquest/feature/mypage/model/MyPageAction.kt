package com.jb.pixelquest.feature.mypage.model


sealed interface MyPageAction {

    data class SelectTab(val tab: MyPageTab) : MyPageAction

    data class SelectArtwork(val artwork: Artwork) : MyPageAction
    object HideArtworkDetail : MyPageAction
    data class DeleteArtwork(val artworkId: String) : MyPageAction
    data class ToggleArtworkVisibility(val artworkId: String) : MyPageAction
    data class SelectSortOption(val option: ArtworkSortOption) : MyPageAction
    data class SelectFilterOption(val option: ArtworkFilterOption?) : MyPageAction
    object RefreshMyArtworks : MyPageAction

    data class SelectCategory(val category: InventoryCategory) : MyPageAction
    data class SelectItem(val item: InventoryItem) : MyPageAction
    object HideItemDetail : MyPageAction
    data class EquipItem(val itemId: String) : MyPageAction
    data class UnequipItem(val itemId: String) : MyPageAction

    data class ShowError(val message: String) : MyPageAction
    object ClearError : MyPageAction
}

