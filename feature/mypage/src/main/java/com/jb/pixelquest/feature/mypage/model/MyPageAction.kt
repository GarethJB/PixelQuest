package com.jb.pixelquest.feature.mypage.model

/**
 * MyPage ?”ë©´???¬ìš©???¡ì…˜
 * State Hoisting ?¨í„´???„í•´ ?¡ì…˜??ëª…ì‹œ?ìœ¼ë¡??•ì˜
 */
sealed interface MyPageAction {
    // ???„í™˜
    data class SelectTab(val tab: MyPageTab) : MyPageAction
    
    // ?˜ì˜ ?‘í’ˆ
    data class SelectArtwork(val artwork: Artwork) : MyPageAction
    object HideArtworkDetail : MyPageAction
    data class DeleteArtwork(val artworkId: String) : MyPageAction
    data class ToggleArtworkVisibility(val artworkId: String) : MyPageAction
    data class SelectSortOption(val option: ArtworkSortOption) : MyPageAction
    data class SelectFilterOption(val option: ArtworkFilterOption?) : MyPageAction
    object RefreshMyArtworks : MyPageAction
    
    // ?¸ë²¤? ë¦¬
    data class SelectCategory(val category: InventoryCategory) : MyPageAction
    data class SelectItem(val item: InventoryItem) : MyPageAction
    object HideItemDetail : MyPageAction
    data class EquipItem(val itemId: String) : MyPageAction
    data class UnequipItem(val itemId: String) : MyPageAction
    
    // ?ëŸ¬ ì²˜ë¦¬
    data class ShowError(val message: String) : MyPageAction
    object ClearError : MyPageAction
}

