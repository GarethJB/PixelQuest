package com.jb.pixelquest.data.remote.android.di

import com.jb.pixelquest.data.local.android.datasource.ArtworkLocalDataSource
import com.jb.pixelquest.data.local.android.datasource.CanvasLocalDataSource
import com.jb.pixelquest.data.local.android.datasource.QuestLocalDataSource
import com.jb.pixelquest.data.local.android.datasource.UserLocalDataSource
import com.jb.pixelquest.data.remote.android.repository.ArtworkRepositoryImpl
import com.jb.pixelquest.data.remote.android.repository.InventoryRepositoryImpl
import com.jb.pixelquest.data.remote.android.repository.QuestRepositoryImpl
import com.jb.pixelquest.data.remote.android.repository.StudioRepositoryImpl
import com.jb.pixelquest.data.remote.android.repository.UserRepositoryImpl
import com.jb.pixelquest.shared.data.remote.datasource.ArtworkRemoteDataSource
import com.jb.pixelquest.shared.data.remote.datasource.InventoryRemoteDataSource
import com.jb.pixelquest.shared.data.remote.datasource.QuestRemoteDataSource
import com.jb.pixelquest.shared.data.remote.datasource.StudioRemoteDataSource
import com.jb.pixelquest.shared.data.remote.datasource.UserRemoteDataSource
import com.jb.pixelquest.shared.domain.repository.artwork.ArtworkRepository
import com.jb.pixelquest.shared.domain.repository.inventory.InventoryRepository
import com.jb.pixelquest.shared.domain.repository.quest.QuestRepository
import com.jb.pixelquest.shared.domain.repository.studio.StudioRepository
import com.jb.pixelquest.shared.domain.repository.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Repository 의존성 주입 모듈
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    
    @Provides
    @Singleton
    fun provideQuestRepository(
        remoteDataSource: QuestRemoteDataSource,
        localDataSource: QuestLocalDataSource
    ): QuestRepository {
        return QuestRepositoryImpl(remoteDataSource, localDataSource)
    }
    
    @Provides
    @Singleton
    fun provideArtworkRepository(
        remoteDataSource: ArtworkRemoteDataSource,
        localDataSource: ArtworkLocalDataSource
    ): ArtworkRepository {
        return ArtworkRepositoryImpl(remoteDataSource, localDataSource)
    }
    
    @Provides
    @Singleton
    fun provideStudioRepository(
        remoteDataSource: StudioRemoteDataSource,
        localDataSource: CanvasLocalDataSource
    ): StudioRepository {
        return StudioRepositoryImpl(remoteDataSource, localDataSource)
    }
    
    @Provides
    @Singleton
    fun provideUserRepository(
        remoteDataSource: UserRemoteDataSource,
        localDataSource: UserLocalDataSource
    ): UserRepository {
        return UserRepositoryImpl(remoteDataSource, localDataSource)
    }
    
    @Provides
    @Singleton
    fun provideInventoryRepository(
        remoteDataSource: InventoryRemoteDataSource
    ): InventoryRepository {
        return InventoryRepositoryImpl(remoteDataSource)
    }
}

