package com.jb.pixelquest.data.remote.android.di

import com.jb.pixelquest.data.remote.android.repository.ArtworkRepositoryImpl
import com.jb.pixelquest.data.remote.android.repository.InventoryRepositoryImpl
import com.jb.pixelquest.data.remote.android.repository.QuestRepositoryImpl
import com.jb.pixelquest.data.remote.android.repository.StudioRepositoryImpl
import com.jb.pixelquest.data.remote.android.repository.UserRepositoryImpl
import com.jb.pixelquest.shared.domain.repository.artwork.ArtworkRepository
import com.jb.pixelquest.shared.domain.repository.inventory.InventoryRepository
import com.jb.pixelquest.shared.domain.repository.quest.QuestRepository
import com.jb.pixelquest.shared.domain.repository.studio.StudioRepository
import com.jb.pixelquest.shared.domain.repository.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Repository 의존성 주입 모듈
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    @Singleton
    abstract fun bindQuestRepository(
        questRepositoryImpl: QuestRepositoryImpl
    ): QuestRepository
    
    @Binds
    @Singleton
    abstract fun bindArtworkRepository(
        artworkRepositoryImpl: ArtworkRepositoryImpl
    ): ArtworkRepository
    
    @Binds
    @Singleton
    abstract fun bindStudioRepository(
        studioRepositoryImpl: StudioRepositoryImpl
    ): StudioRepository
    
    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
    
    @Binds
    @Singleton
    abstract fun bindInventoryRepository(
        inventoryRepositoryImpl: InventoryRepositoryImpl
    ): InventoryRepository
}

