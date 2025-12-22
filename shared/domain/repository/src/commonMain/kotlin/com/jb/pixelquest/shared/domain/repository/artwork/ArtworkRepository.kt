package com.jb.pixelquest.shared.domain.repository.artwork

import com.jb.pixelquest.shared.domain.model.artwork.Artwork
import com.jb.pixelquest.shared.domain.model.artwork.ArtworkCategory

/**
 * 작품 관련 데이터를 관리하는 Repository 인터페이스
 */
interface ArtworkRepository {
    /**
     * 인기 작품 목록 조회
     */
    suspend fun getTrendingArtworks(limit: Int = 20): Result<List<Artwork>>

    /**
     * 최신 작품 목록 조회
     */
    suspend fun getLatestArtworks(limit: Int = 20): Result<List<Artwork>>

    /**
     * 카테고리별 작품 목록 조회
     */
    suspend fun getCategoryArtworks(
        category: ArtworkCategory,
        limit: Int = 20
    ): Result<List<Artwork>>

    /**
     * 작품 검색
     */
    suspend fun searchArtworks(
        query: String,
        limit: Int = 20
    ): Result<List<Artwork>>

    /**
     * 작품 ID로 조회
     */
    suspend fun getArtworkById(id: String): Result<Artwork>

    /**
     * 내 작품 목록 조회
     */
    suspend fun getMyArtworks(): Result<List<Artwork>>

    /**
     * 작품 생성
     */
    suspend fun createArtwork(artwork: Artwork): Result<Artwork>

    /**
     * 작품 수정
     */
    suspend fun updateArtwork(artwork: Artwork): Result<Artwork>

    /**
     * 작품 삭제
     */
    suspend fun deleteArtwork(id: String): Result<Unit>

    /**
     * 작품 좋아요 토글
     */
    suspend fun toggleLike(artworkId: String): Result<Boolean>

    /**
     * 작품 북마크 토글
     */
    suspend fun toggleBookmark(artworkId: String): Result<Boolean>

    /**
     * 작품 공개/비공개 토글
     */
    suspend fun toggleArtworkVisibility(artworkId: String): Result<Boolean>
}

