package com.jb.pixelquest.feature.gallery.model

/**
 * Gallery ?”ë©´???¬ìš©???¡ì…˜
 * State Hoisting ?¨í„´???„í•´ ?¡ì…˜??ëª…ì‹œ?ìœ¼ë¡??•ì˜
 */
sealed interface GalleryAction {
    // ???„í™˜
    data class SelectTab(val tab: GalleryTab) : GalleryAction
    
    // ?‘í’ˆ ëª©ë¡
    object RefreshArtworks : GalleryAction
    data class LoadMoreArtworks(val tab: GalleryTab) : GalleryAction
    
    // ?‘í’ˆ ? íƒ
    data class SelectArtwork(val artwork: Artwork) : GalleryAction
    object HideArtworkDetail : GalleryAction
    
    // ì¹´í…Œê³ ë¦¬
    data class SelectCategory(val category: ArtworkCategory) : GalleryAction
    object ClearCategory : GalleryAction
    
    // ê²€??
    data class UpdateSearchQuery(val query: String) : GalleryAction
    object PerformSearch : GalleryAction
    object ClearSearch : GalleryAction
    object ToggleSearch : GalleryAction
    
    // ?‘í’ˆ ?í˜¸?‘ìš©
    data class ToggleLike(val artworkId: String) : GalleryAction
    data class ToggleBookmark(val artworkId: String) : GalleryAction
    data class ShareArtwork(val artworkId: String) : GalleryAction
    
    // ?ëŸ¬ ì²˜ë¦¬
    data class ShowError(val message: String) : GalleryAction
    object ClearError : GalleryAction
}

