package com.jb.pixelquest.feature.mypage.model

sealed interface MyPageSideEffect {

    data class NavigateToStudio(val artworkId: String? = null) : MyPageSideEffect

    data class NavigateToArtworkDetail(val artworkId: String) : MyPageSideEffect

    data class ShowSnackbar(val message: String) : MyPageSideEffect
}

