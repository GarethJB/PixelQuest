package com.jb.pixelquest.core.di

import com.jb.pixelquest.shared.domain.repository.artwork.ArtworkRepository
import com.jb.pixelquest.shared.domain.repository.inventory.InventoryRepository
import com.jb.pixelquest.shared.domain.repository.quest.QuestRepository
import com.jb.pixelquest.shared.domain.repository.studio.StudioRepository
import com.jb.pixelquest.shared.domain.repository.user.UserRepository
import com.jb.pixelquest.shared.domain.usecase.artwork.CreateArtworkUseCase
import com.jb.pixelquest.shared.domain.usecase.artwork.DeleteArtworkUseCase
import com.jb.pixelquest.shared.domain.usecase.artwork.GetArtworkByIdUseCase
import com.jb.pixelquest.shared.domain.usecase.artwork.GetCategoryArtworksUseCase
import com.jb.pixelquest.shared.domain.usecase.artwork.GetLatestArtworksUseCase
import com.jb.pixelquest.shared.domain.usecase.artwork.GetMyArtworksUseCase
import com.jb.pixelquest.shared.domain.usecase.artwork.GetTrendingArtworksUseCase
import com.jb.pixelquest.shared.domain.usecase.artwork.SearchArtworksUseCase
import com.jb.pixelquest.shared.domain.usecase.artwork.ToggleArtworkVisibilityUseCase
import com.jb.pixelquest.shared.domain.usecase.artwork.ToggleBookmarkUseCase
import com.jb.pixelquest.shared.domain.usecase.artwork.ToggleLikeUseCase
import com.jb.pixelquest.shared.domain.usecase.artwork.UpdateArtworkUseCase
import com.jb.pixelquest.shared.domain.usecase.inventory.EquipItemUseCase
import com.jb.pixelquest.shared.domain.usecase.inventory.GetEquippedItemsUseCase
import com.jb.pixelquest.shared.domain.usecase.inventory.GetInventoryItemByIdUseCase
import com.jb.pixelquest.shared.domain.usecase.inventory.GetInventoryItemsByTypeUseCase
import com.jb.pixelquest.shared.domain.usecase.inventory.GetInventoryItemsUseCase
import com.jb.pixelquest.shared.domain.usecase.inventory.UnequipItemUseCase
import com.jb.pixelquest.shared.domain.usecase.quest.ClaimRewardUseCase
import com.jb.pixelquest.shared.domain.usecase.quest.CompleteQuestUseCase
import com.jb.pixelquest.shared.domain.usecase.quest.GetAchievementsUseCase
import com.jb.pixelquest.shared.domain.usecase.quest.GetActiveQuestsUseCase
import com.jb.pixelquest.shared.domain.usecase.quest.GetCompletedQuestsUseCase
import com.jb.pixelquest.shared.domain.usecase.quest.GetDailyQuestsUseCase
import com.jb.pixelquest.shared.domain.usecase.quest.GetQuestByIdUseCase
import com.jb.pixelquest.shared.domain.usecase.quest.GetQuestStatisticsUseCase
import com.jb.pixelquest.shared.domain.usecase.quest.GetRecentActivitiesUseCase
import com.jb.pixelquest.shared.domain.usecase.quest.GetUserQuestProgressUseCase
import com.jb.pixelquest.shared.domain.usecase.quest.GetWeeklyQuestsUseCase
import com.jb.pixelquest.shared.domain.usecase.quest.StartQuestUseCase
import com.jb.pixelquest.shared.domain.usecase.studio.CreateCanvasUseCase
import com.jb.pixelquest.shared.domain.usecase.studio.DeleteCanvasUseCase
import com.jb.pixelquest.shared.domain.usecase.studio.GetBrushByIdUseCase
import com.jb.pixelquest.shared.domain.usecase.studio.GetBrushesUseCase
import com.jb.pixelquest.shared.domain.usecase.studio.GetCanvasByIdUseCase
import com.jb.pixelquest.shared.domain.usecase.studio.GetCanvasesUseCase
import com.jb.pixelquest.shared.domain.usecase.studio.GetPaletteByIdUseCase
import com.jb.pixelquest.shared.domain.usecase.studio.GetPalettesUseCase
import com.jb.pixelquest.shared.domain.usecase.studio.UpdateCanvasUseCase
import com.jb.pixelquest.shared.domain.usecase.user.GetCurrentUserUseCase
import com.jb.pixelquest.shared.domain.usecase.user.GetUserByIdUseCase
import com.jb.pixelquest.shared.domain.usecase.user.UpdateUserAvatarUseCase
import com.jb.pixelquest.shared.domain.usecase.user.UpdateUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * UseCase 의존성 주입 모듈
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    
    // Quest UseCases
    @Provides
    @Singleton
    fun provideGetDailyQuestsUseCase(repository: QuestRepository): GetDailyQuestsUseCase {
        return GetDailyQuestsUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetWeeklyQuestsUseCase(repository: QuestRepository): GetWeeklyQuestsUseCase {
        return GetWeeklyQuestsUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetActiveQuestsUseCase(repository: QuestRepository): GetActiveQuestsUseCase {
        return GetActiveQuestsUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetCompletedQuestsUseCase(repository: QuestRepository): GetCompletedQuestsUseCase {
        return GetCompletedQuestsUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetQuestByIdUseCase(repository: QuestRepository): GetQuestByIdUseCase {
        return GetQuestByIdUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideStartQuestUseCase(repository: QuestRepository): StartQuestUseCase {
        return StartQuestUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideCompleteQuestUseCase(repository: QuestRepository): CompleteQuestUseCase {
        return CompleteQuestUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetUserQuestProgressUseCase(repository: QuestRepository): GetUserQuestProgressUseCase {
        return GetUserQuestProgressUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetQuestStatisticsUseCase(repository: QuestRepository): GetQuestStatisticsUseCase {
        return GetQuestStatisticsUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetAchievementsUseCase(repository: QuestRepository): GetAchievementsUseCase {
        return GetAchievementsUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetRecentActivitiesUseCase(repository: QuestRepository): GetRecentActivitiesUseCase {
        return GetRecentActivitiesUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideClaimRewardUseCase(repository: QuestRepository): ClaimRewardUseCase {
        return ClaimRewardUseCase(repository)
    }
    
    // Artwork UseCases
    @Provides
    @Singleton
    fun provideGetTrendingArtworksUseCase(repository: ArtworkRepository): GetTrendingArtworksUseCase {
        return GetTrendingArtworksUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetLatestArtworksUseCase(repository: ArtworkRepository): GetLatestArtworksUseCase {
        return GetLatestArtworksUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetCategoryArtworksUseCase(repository: ArtworkRepository): GetCategoryArtworksUseCase {
        return GetCategoryArtworksUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideSearchArtworksUseCase(repository: ArtworkRepository): SearchArtworksUseCase {
        return SearchArtworksUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetArtworkByIdUseCase(repository: ArtworkRepository): GetArtworkByIdUseCase {
        return GetArtworkByIdUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetMyArtworksUseCase(repository: ArtworkRepository): GetMyArtworksUseCase {
        return GetMyArtworksUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideCreateArtworkUseCase(repository: ArtworkRepository): CreateArtworkUseCase {
        return CreateArtworkUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideUpdateArtworkUseCase(repository: ArtworkRepository): UpdateArtworkUseCase {
        return UpdateArtworkUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideDeleteArtworkUseCase(repository: ArtworkRepository): DeleteArtworkUseCase {
        return DeleteArtworkUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideToggleLikeUseCase(repository: ArtworkRepository): ToggleLikeUseCase {
        return ToggleLikeUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideToggleBookmarkUseCase(repository: ArtworkRepository): ToggleBookmarkUseCase {
        return ToggleBookmarkUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideToggleArtworkVisibilityUseCase(repository: ArtworkRepository): ToggleArtworkVisibilityUseCase {
        return ToggleArtworkVisibilityUseCase(repository)
    }
    
    // Studio UseCases
    @Provides
    @Singleton
    fun provideGetCanvasesUseCase(repository: StudioRepository): GetCanvasesUseCase {
        return GetCanvasesUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetCanvasByIdUseCase(repository: StudioRepository): GetCanvasByIdUseCase {
        return GetCanvasByIdUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideCreateCanvasUseCase(repository: StudioRepository): CreateCanvasUseCase {
        return CreateCanvasUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideUpdateCanvasUseCase(repository: StudioRepository): UpdateCanvasUseCase {
        return UpdateCanvasUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideDeleteCanvasUseCase(repository: StudioRepository): DeleteCanvasUseCase {
        return DeleteCanvasUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetPalettesUseCase(repository: StudioRepository): GetPalettesUseCase {
        return GetPalettesUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetPaletteByIdUseCase(repository: StudioRepository): GetPaletteByIdUseCase {
        return GetPaletteByIdUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetBrushesUseCase(repository: StudioRepository): GetBrushesUseCase {
        return GetBrushesUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetBrushByIdUseCase(repository: StudioRepository): GetBrushByIdUseCase {
        return GetBrushByIdUseCase(repository)
    }
    
    // User UseCases
    @Provides
    @Singleton
    fun provideGetCurrentUserUseCase(repository: UserRepository): GetCurrentUserUseCase {
        return GetCurrentUserUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetUserByIdUseCase(repository: UserRepository): GetUserByIdUseCase {
        return GetUserByIdUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideUpdateUserUseCase(repository: UserRepository): UpdateUserUseCase {
        return UpdateUserUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideUpdateUserAvatarUseCase(repository: UserRepository): UpdateUserAvatarUseCase {
        return UpdateUserAvatarUseCase(repository)
    }
    
    // Inventory UseCases
    @Provides
    @Singleton
    fun provideGetInventoryItemsUseCase(repository: InventoryRepository): GetInventoryItemsUseCase {
        return GetInventoryItemsUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetInventoryItemsByTypeUseCase(repository: InventoryRepository): GetInventoryItemsByTypeUseCase {
        return GetInventoryItemsByTypeUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetInventoryItemByIdUseCase(repository: InventoryRepository): GetInventoryItemByIdUseCase {
        return GetInventoryItemByIdUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideEquipItemUseCase(repository: InventoryRepository): EquipItemUseCase {
        return EquipItemUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideUnequipItemUseCase(repository: InventoryRepository): UnequipItemUseCase {
        return UnequipItemUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetEquippedItemsUseCase(repository: InventoryRepository): GetEquippedItemsUseCase {
        return GetEquippedItemsUseCase(repository)
    }
}

