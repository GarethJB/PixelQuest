package com.jb.pixelquest.feature.gallery.model

/**
 * Gallery ?”ë©´???¬ì´???´í™??
 * ?¤ë¹„ê²Œì´?? ?ëŸ¬ ?œì‹œ ??UI ?´ë²¤??
 */
sealed interface GallerySideEffect {
    /**
     * ?¬ìš©???„ë¡œ?„ë¡œ ?´ë™
     */
    data class NavigateToUserProfile(val userId: String) : GallerySideEffect
    
    /**
     * Studio ?ë””?°ë¡œ ?´ë™ (?‘í’ˆ ?¸ì§‘/ë³µì œ)
     */
    data class NavigateToStudio(val artworkId: String? = null) : GallerySideEffect
    
    /**
     * ?¤ë‚µë°??œì‹œ
     */
    data class ShowSnackbar(val message: String) : GallerySideEffect
    
    /**
     * ?‘í’ˆ ê³µìœ 
     */
    data class ShareArtwork(val artwork: Artwork) : GallerySideEffect
}

