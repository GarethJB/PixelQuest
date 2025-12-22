package com.jb.pixelquest.shared.domain.repository.studio

import com.jb.pixelquest.shared.domain.model.studio.Brush
import com.jb.pixelquest.shared.domain.model.studio.Canvas
import com.jb.pixelquest.shared.domain.model.studio.Palette

/**
 * 스튜디오 관련 데이터를 관리하는 Repository 인터페이스
 */
interface StudioRepository {
    /**
     * 캔버스 목록 조회
     */
    suspend fun getCanvases(): Result<List<Canvas>>

    /**
     * 캔버스 ID로 조회
     */
    suspend fun getCanvasById(id: String): Result<Canvas>

    /**
     * 캔버스 생성
     */
    suspend fun createCanvas(canvas: Canvas): Result<Canvas>

    /**
     * 캔버스 수정
     */
    suspend fun updateCanvas(canvas: Canvas): Result<Canvas>

    /**
     * 캔버스 삭제
     */
    suspend fun deleteCanvas(id: String): Result<Unit>

    /**
     * 팔레트 목록 조회
     */
    suspend fun getPalettes(): Result<List<Palette>>

    /**
     * 팔레트 ID로 조회
     */
    suspend fun getPaletteById(id: String): Result<Palette>

    /**
     * 브러시 목록 조회
     */
    suspend fun getBrushes(): Result<List<Brush>>

    /**
     * 브러시 ID로 조회
     */
    suspend fun getBrushById(id: String): Result<Brush>
}

