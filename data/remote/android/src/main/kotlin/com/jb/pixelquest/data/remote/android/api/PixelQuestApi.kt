package com.jb.pixelquest.data.remote.android.api

import com.jb.pixelquest.shared.data.remote.dto.ArtworkDto
import com.jb.pixelquest.shared.data.remote.dto.QuestDto
import com.jb.pixelquest.shared.data.remote.dto.UserDto
import com.jb.pixelquest.shared.data.remote.dto.InventoryItemDto
import com.jb.pixelquest.shared.data.remote.dto.CanvasDto
import com.jb.pixelquest.shared.data.remote.dto.PaletteDto
import com.jb.pixelquest.shared.data.remote.dto.BrushDto
import com.jb.pixelquest.shared.data.remote.dto.UserQuestProgressDto
import com.jb.pixelquest.shared.data.remote.dto.QuestStatisticsDto
import com.jb.pixelquest.shared.data.remote.dto.AchievementDto
import com.jb.pixelquest.shared.data.remote.dto.ActivityDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * PixelQuest API 서비스 인터페이스
 */
interface PixelQuestApi {
    // ========== Artwork APIs ==========
    
    @GET("artworks/trending")
    suspend fun getTrendingArtworks(
        @Query("limit") limit: Int = 20
    ): List<ArtworkDto>
    
    @GET("artworks/latest")
    suspend fun getLatestArtworks(
        @Query("limit") limit: Int = 20
    ): List<ArtworkDto>
    
    @GET("artworks/category/{category}")
    suspend fun getCategoryArtworks(
        @Path("category") category: String,
        @Query("limit") limit: Int = 20
    ): List<ArtworkDto>
    
    @GET("artworks/search")
    suspend fun searchArtworks(
        @Query("q") query: String,
        @Query("limit") limit: Int = 20
    ): List<ArtworkDto>
    
    @GET("artworks/{id}")
    suspend fun getArtworkById(@Path("id") id: String): ArtworkDto
    
    @GET("artworks/my")
    suspend fun getMyArtworks(): List<ArtworkDto>
    
    @POST("artworks")
    suspend fun createArtwork(@Body artwork: ArtworkDto): ArtworkDto
    
    @PUT("artworks/{id}")
    suspend fun updateArtwork(
        @Path("id") id: String,
        @Body artwork: ArtworkDto
    ): ArtworkDto
    
    @DELETE("artworks/{id}")
    suspend fun deleteArtwork(@Path("id") id: String): Unit
    
    @POST("artworks/{id}/like")
    suspend fun toggleLike(@Path("id") id: String): Map<String, Boolean>
    
    @POST("artworks/{id}/bookmark")
    suspend fun toggleBookmark(@Path("id") id: String): Map<String, Boolean>
    
    @PUT("artworks/{id}/visibility")
    suspend fun toggleArtworkVisibility(@Path("id") id: String): Map<String, Boolean>
    
    // ========== Quest APIs ==========
    
    @GET("quests/daily")
    suspend fun getDailyQuests(): List<QuestDto>
    
    @GET("quests/weekly")
    suspend fun getWeeklyQuests(): List<QuestDto>
    
    @GET("quests/active")
    suspend fun getActiveQuests(): List<QuestDto>
    
    @GET("quests/completed")
    suspend fun getCompletedQuests(): List<QuestDto>
    
    @GET("quests/{id}")
    suspend fun getQuestById(@Path("id") id: String): QuestDto
    
    @POST("quests/{id}/start")
    suspend fun startQuest(@Path("id") id: String): QuestDto
    
    @POST("quests/{id}/complete")
    suspend fun completeQuest(
        @Path("id") questId: String,
        @Query("artworkId") artworkId: String
    ): QuestDto
    
    @GET("quests/progress")
    suspend fun getUserQuestProgress(): UserQuestProgressDto
    
    @GET("quests/statistics")
    suspend fun getQuestStatistics(): QuestStatisticsDto
    
    @GET("quests/achievements")
    suspend fun getAchievements(): List<AchievementDto>
    
    @GET("quests/activities")
    suspend fun getRecentActivities(@Query("limit") limit: Int = 20): List<ActivityDto>
    
    @POST("rewards/{id}/claim")
    suspend fun claimReward(@Path("id") id: String): Unit
    
    // ========== User APIs ==========
    
    @GET("users/me")
    suspend fun getCurrentUser(): UserDto
    
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: String): UserDto
    
    @PUT("users/me")
    suspend fun updateUser(@Body user: UserDto): UserDto
    
    @PUT("users/me/avatar")
    suspend fun updateUserAvatar(@Query("avatarUrl") avatarUrl: String): UserDto
    
    // ========== Inventory APIs ==========
    
    @GET("inventory/items")
    suspend fun getInventoryItems(): List<InventoryItemDto>
    
    @GET("inventory/items/type/{type}")
    suspend fun getInventoryItemsByType(@Path("type") type: String): List<InventoryItemDto>
    
    @GET("inventory/items/{id}")
    suspend fun getInventoryItemById(@Path("id") id: String): InventoryItemDto
    
    @POST("inventory/items/{id}/equip")
    suspend fun equipItem(@Path("id") id: String): InventoryItemDto
    
    @POST("inventory/items/{id}/unequip")
    suspend fun unequipItem(@Path("id") id: String): InventoryItemDto
    
    @GET("inventory/items/equipped")
    suspend fun getEquippedItems(): List<InventoryItemDto>
    
    // ========== Studio APIs ==========
    
    @GET("studio/canvases")
    suspend fun getCanvases(): List<CanvasDto>
    
    @GET("studio/canvases/{id}")
    suspend fun getCanvasById(@Path("id") id: String): CanvasDto
    
    @POST("studio/canvases")
    suspend fun createCanvas(@Body canvas: CanvasDto): CanvasDto
    
    @PUT("studio/canvases/{id}")
    suspend fun updateCanvas(
        @Path("id") id: String,
        @Body canvas: CanvasDto
    ): CanvasDto
    
    @DELETE("studio/canvases/{id}")
    suspend fun deleteCanvas(@Path("id") id: String): Unit
    
    @GET("studio/palettes")
    suspend fun getPalettes(): List<PaletteDto>
    
    @GET("studio/palettes/{id}")
    suspend fun getPaletteById(@Path("id") id: String): PaletteDto
    
    @GET("studio/brushes")
    suspend fun getBrushes(): List<BrushDto>
    
    @GET("studio/brushes/{id}")
    suspend fun getBrushById(@Path("id") id: String): BrushDto
}

