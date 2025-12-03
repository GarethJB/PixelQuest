package com.jb.pixelquest.feature.mypage.model

/**
 * MyPage 화면의 사용자 액션
 * State Hoisting 패턴을 위해 액션을 명시적으로 정의
 */
sealed interface MyPageAction {
    // 탭 전환
    data class SelectTab(val tab: MyPageTab) : MyPageAction
    
    // 나의 작품
    data class SelectArtwork(val artwork: Artwork) : MyPageAction
    object HideArtworkDetail : MyPageAction
    data class DeleteArtwork(val artworkId: String) : MyPageAction
    data class ToggleArtworkVisibility(val artworkId: String) : MyPageAction
    data class SelectSortOption(val option: ArtworkSortOption) : MyPageAction
    data class SelectFilterOption(val option: ArtworkFilterOption?) : MyPageAction
    object RefreshMyArtworks : MyPageAction
    
    // 인벤토리
    data class SelectCategory(val category: InventoryCategory) : MyPageAction
    data class SelectItem(val item: InventoryItem) : MyPageAction
    object HideItemDetail : MyPageAction
    data class EquipItem(val itemId: String) : MyPageAction
    data class UnequipItem(val itemId: String) : MyPageAction
    
    // 에러 처리
    data class ShowError(val message: String) : MyPageAction
    object ClearError : MyPageAction
}

