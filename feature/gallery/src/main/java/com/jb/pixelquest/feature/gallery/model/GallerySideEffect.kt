package com.jb.pixelquest.feature.gallery.model

sealed interface GallerySideEffect {

    data class NavigateToUserProfile(val userId: String) : GallerySideEffect

    data class NavigateToStudio(val artworkId: String? = null) : GallerySideEffect

    data class ShowSnackbar(val message: String) : GallerySideEffect

    data class ShareArtwork(val artwork: Artwork) : GallerySideEffect
}