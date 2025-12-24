package com.jb.pixelquest.feature.gallery.model

sealed interface GalleryAction {
    data class SelectTab(val tab: GalleryTab) : GalleryAction

    object RefreshArtworks : GalleryAction
    data class LoadMoreArtworks(val tab: GalleryTab) : GalleryAction

    data class SelectArtwork(val artwork: Artwork) : GalleryAction
    object HideArtworkDetail : GalleryAction

    data class SelectCategory(val category: ArtworkCategory) : GalleryAction
    object ClearCategory : GalleryAction

    data class UpdateSearchQuery(val query: String) : GalleryAction
    object PerformSearch : GalleryAction
    object ClearSearch : GalleryAction
    object ToggleSearch : GalleryAction

    data class ToggleLike(val artworkId: String) : GalleryAction
    data class ToggleBookmark(val artworkId: String) : GalleryAction
    data class ShareArtwork(val artworkId: String) : GalleryAction

    data class ShowError(val message: String) : GalleryAction
    object ClearError : GalleryAction
}

